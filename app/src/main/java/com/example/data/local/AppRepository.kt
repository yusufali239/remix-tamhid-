package com.example.data.local

import android.content.Context
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.json.JSONArray
import org.json.JSONObject

enum class SrsRating {
    AGAIN, // Qaytadan (eslay olmadim)
    GOOD,  // Yaxshi (esladim)
    EASY   // Oson (juda yaxshi bilaman)
}

class AppRepository(context: Context) {
    private val db = TamhidDatabase.getDatabase(context)
    private val bookmarkDao = db.bookmarkDao()
    private val noteDao = db.noteDao()
    private val flashcardProgressDao = db.flashcardProgressDao()
    private val readingProgressDao = db.readingProgressDao()
    private val progressDao = db.progressDao()
    private val favoriteDao = db.favoriteDao()
    private val quizDao = db.quizDao()
    private val dictionaryDao = db.dictionaryDao()
    private val dailyActivityDao = db.dailyActivityDao()

    // ========================================================================
    // Daily Activity & Streak Tracker
    // ========================================================================
    fun getAllActivityDates(): Flow<List<String>> = dailyActivityDao.getAllActivityDates()

    suspend fun recordTodayActivity() {
        val todayStr = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())
        dailyActivityDao.recordActivity(DailyActivityEntity(date = todayStr))
    }

    // Knowledge base
    fun getCourse(): MadrasaCourse = TamhidKnowledgeBase.course
    fun getAllChapters(): List<Chapter> = TamhidKnowledgeBase.getAllChapters()
    fun getChapter(id: String): Chapter? = TamhidKnowledgeBase.getChapter(id)
    fun getLesson(id: String): Lesson? = TamhidKnowledgeBase.getLesson(id)
    fun getAllFlashcards(): List<FlashcardItem> = TamhidKnowledgeBase.getAllFlashcards()
    fun getAllQuizzes(): List<QuizQuestion> = TamhidKnowledgeBase.getAllQuizzes()

    // ========================================================================
    // Modern Bookmarks (Room v2)
    // ========================================================================
    fun getAllBookmarks(): Flow<List<Bookmark>> = bookmarkDao.getAllBookmarks()

    fun getBookmarksForChapter(chapterId: String): Flow<List<Bookmark>> =
        bookmarkDao.getBookmarksForChapter(chapterId)

    fun isBookmarked(id: String): Flow<Boolean> = bookmarkDao.isBookmarked(id)

    suspend fun toggleBookmark(
        id: String,
        chapterId: String,
        sectionId: String,
        arabicQuote: String,
        translation: String,
        pageNumber: Int,
        arabicFullText: String = arabicQuote,
        userNote: String = "",
        tags: String = ""
    ) {
        val existing = bookmarkDao.getBookmarkById(id)
        if (existing != null) {
            bookmarkDao.deleteBookmark(existing)
        } else {
            bookmarkDao.insertBookmark(
                Bookmark(
                    id = id,
                    chapterId = chapterId,
                    sectionId = sectionId,
                    arabicQuote = arabicQuote,
                    arabicFullText = arabicFullText.ifBlank { arabicQuote },
                    translation = translation,
                    pageNumber = pageNumber,
                    userNote = userNote,
                    timestamp = System.currentTimeMillis(),
                    tags = tags
                )
            )
        }
    }

    suspend fun removeBookmark(id: String) = bookmarkDao.deleteBookmarkById(id)

    suspend fun updateBookmarkNote(id: String, note: String) = bookmarkDao.updateBookmarkNote(id, note)

    suspend fun getBookmarkById(id: String): Bookmark? = bookmarkDao.getBookmarkById(id)

    fun searchBookmarks(query: String): Flow<List<Bookmark>> = bookmarkDao.searchBookmarks(query)

    // ========================================================================
    // Modern Notes (Room v2)
    // ========================================================================
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    fun getNotesForChapter(chapterId: String): Flow<List<Note>> =
        noteDao.getNotesForChapter(chapterId)

    suspend fun saveNote(chapterId: String, text: String): Long {
        return noteDao.insertNote(
            Note(
                chapterId = chapterId,
                text = text,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun deleteNote(id: Long) = noteDao.deleteNoteById(id)

    // ========================================================================
    // Reading Progress (Room v2)
    // ========================================================================
    fun getAllReadingProgress(): Flow<List<ReadingProgress>> = readingProgressDao.getAllReadingProgress()

    fun observeReadingProgress(chapterId: String): Flow<ReadingProgress?> =
        readingProgressDao.observeProgressForChapter(chapterId)

    suspend fun saveReadingProgress(chapterId: String, percent: Int, lastPage: Int) {
        readingProgressDao.saveReadingProgress(
            ReadingProgress(
                chapterId = chapterId,
                percent = percent.coerceIn(0, 100),
                lastPage = lastPage
            )
        )
    }

    // ========================================================================
    // Flashcard SRS (Anki algorithm)
    // ========================================================================
    fun getAllFlashcardProgress(): Flow<List<FlashcardProgress>> =
        flashcardProgressDao.getAllProgress()

    fun countDueFlashcards(currentTime: Long = System.currentTimeMillis()): Flow<Int> =
        flashcardProgressDao.countDueCards(currentTime)

    suspend fun recordFlashcardReview(cardId: String, rating: SrsRating) {
        val current = flashcardProgressDao.getProgressForCard(cardId)
        val level = current?.level ?: 0
        val ease = current?.easeFactor ?: 2.5f
        val now = System.currentTimeMillis()

        val updated = when (rating) {
            SrsRating.AGAIN -> {
                val newEase = maxOf(1.3f, ease - 0.2f)
                val nextDate = now + 10 * 60 * 1000L // 10 min
                FlashcardProgress(cardId, level = 0, nextReviewDate = nextDate, easeFactor = newEase)
            }
            SrsRating.GOOD -> {
                val newLevel = level + 1
                val intervalDays = when (newLevel) {
                    1 -> 1
                    2 -> 3
                    else -> (3 * Math.pow(ease.toDouble(), (newLevel - 2).toDouble())).toInt().coerceAtLeast(1)
                }
                val nextDate = now + intervalDays * 24 * 60 * 60 * 1000L
                FlashcardProgress(cardId, level = newLevel, nextReviewDate = nextDate, easeFactor = ease)
            }
            SrsRating.EASY -> {
                val newLevel = level + 2
                val newEase = ease + 0.15f
                val intervalDays = when (newLevel) {
                    1 -> 2
                    2 -> 4
                    else -> (4 * Math.pow(newEase.toDouble(), (newLevel - 2).toDouble()) * 1.3).toInt().coerceAtLeast(2)
                }
                val nextDate = now + intervalDays * 24 * 60 * 60 * 1000L
                FlashcardProgress(cardId, level = newLevel, nextReviewDate = nextDate, easeFactor = newEase)
            }
        }
        flashcardProgressDao.saveProgress(updated)
    }

    // ========================================================================
    // JSON Backup Export & Import (100% Data Safety)
    // ========================================================================
    suspend fun exportUserDataToJson(): String {
        val root = JSONObject()
        root.put("version", 2)
        root.put("app", "At-Tamhid")
        root.put("exportTimestamp", System.currentTimeMillis())

        // Bookmarks
        val bookmarksList = bookmarkDao.getAllBookmarks().first()
        val bookmarksArray = JSONArray()
        bookmarksList.forEach { b ->
            val obj = JSONObject()
            obj.put("id", b.id)
            obj.put("chapterId", b.chapterId)
            obj.put("sectionId", b.sectionId)
            obj.put("arabicQuote", b.arabicQuote)
            obj.put("translation", b.translation)
            obj.put("pageNumber", b.pageNumber)
            obj.put("timestamp", b.timestamp)
            bookmarksArray.put(obj)
        }
        root.put("bookmarks", bookmarksArray)

        // Notes
        val notesList = noteDao.getAllNotes().first()
        val notesArray = JSONArray()
        notesList.forEach { n ->
            val obj = JSONObject()
            obj.put("id", n.id)
            obj.put("chapterId", n.chapterId)
            obj.put("text", n.text)
            obj.put("timestamp", n.timestamp)
            notesArray.put(obj)
        }
        root.put("notes", notesArray)

        // Reading Progress
        val progressList = readingProgressDao.getAllReadingProgress().first()
        val progressArray = JSONArray()
        progressList.forEach { p ->
            val obj = JSONObject()
            obj.put("chapterId", p.chapterId)
            obj.put("percent", p.percent)
            obj.put("lastPage", p.lastPage)
            progressArray.put(obj)
        }
        root.put("readingProgress", progressArray)

        // Flashcards SRS
        val flashcardsList = flashcardProgressDao.getAllProgress().first()
        val flashcardsArray = JSONArray()
        flashcardsList.forEach { f ->
            val obj = JSONObject()
            obj.put("cardId", f.cardId)
            obj.put("level", f.level)
            obj.put("nextReviewDate", f.nextReviewDate)
            obj.put("easeFactor", f.easeFactor.toDouble())
            flashcardsArray.put(obj)
        }
        root.put("flashcardProgress", flashcardsArray)

        return root.toString(2)
    }

    suspend fun importUserDataFromJson(jsonString: String): Boolean {
        return try {
            val root = JSONObject(jsonString)

            if (root.has("bookmarks")) {
                val array = root.getJSONArray("bookmarks")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val bookmark = Bookmark(
                        id = obj.getString("id"),
                        chapterId = obj.getString("chapterId"),
                        sectionId = obj.optString("sectionId", "1"),
                        arabicQuote = obj.optString("arabicQuote", ""),
                        translation = obj.optString("translation", ""),
                        pageNumber = obj.optInt("pageNumber", 1),
                        timestamp = obj.optLong("timestamp", System.currentTimeMillis())
                    )
                    bookmarkDao.insertBookmark(bookmark)
                }
            }

            if (root.has("notes")) {
                val array = root.getJSONArray("notes")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val note = Note(
                        chapterId = obj.getString("chapterId"),
                        text = obj.getString("text"),
                        timestamp = obj.optLong("timestamp", System.currentTimeMillis())
                    )
                    noteDao.insertNote(note)
                }
            }

            if (root.has("readingProgress")) {
                val array = root.getJSONArray("readingProgress")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val progress = ReadingProgress(
                        chapterId = obj.getString("chapterId"),
                        percent = obj.optInt("percent", 0),
                        lastPage = obj.optInt("lastPage", 1)
                    )
                    readingProgressDao.saveReadingProgress(progress)
                }
            }

            if (root.has("flashcardProgress")) {
                val array = root.getJSONArray("flashcardProgress")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val flashcard = FlashcardProgress(
                        cardId = obj.getString("cardId"),
                        level = obj.optInt("level", 0),
                        nextReviewDate = obj.optLong("nextReviewDate", 0L),
                        easeFactor = obj.optDouble("easeFactor", 2.5).toFloat()
                    )
                    flashcardProgressDao.saveProgress(flashcard)
                }
            }

            true
        } catch (e: Exception) {
            false
        }
    }

    // Auxiliary Quizzes & Progress
    fun getAllQuizResults(): Flow<List<QuizResultEntity>> = quizDao.getAllQuizResults()
    fun getTotalQuizzesTaken(): Flow<Int> = quizDao.getTotalQuizzesTaken()
    suspend fun saveQuizResult(quizKey: String, total: Int, correct: Int, wrongIds: List<String>) {
        val pct = if (total > 0) (correct * 100) / total else 0
        quizDao.saveQuizResult(
            QuizResultEntity(
                quizKey = quizKey,
                totalQuestions = total,
                correctAnswers = correct,
                scorePercentage = pct,
                wrongQuestionIdsCsv = wrongIds.joinToString(",")
            )
        )
    }

    fun getAllProgress(): Flow<List<LessonProgressEntity>> = progressDao.getAllProgress()
    fun getCompletedLessonsCount(): Flow<Int> = progressDao.getCompletedLessonsCount()

    // ========================================================================
    // Lug'atlar (Room DictionaryDao)
    // ========================================================================
    fun getAllDictionaryWords(): Flow<List<DictionaryWord>> = dictionaryDao.getAllWords()

    fun searchDictionaryWords(query: String): Flow<List<DictionaryWord>> = dictionaryDao.searchWords(query)

    suspend fun insertDictionaryWord(word: DictionaryWord) {
        dictionaryDao.insertWord(word)
    }

    suspend fun deleteDictionaryWord(word: DictionaryWord) {
        dictionaryDao.deleteWord(word)
    }

    suspend fun seedInitialDictionaryWordsIfEmpty() {
        if (dictionaryDao.getCount() > 0) return

        val initialWords = listOf(
            DictionaryWord("w1", "الحقيقة", "Haqiqat", "Narsaning o'zi qanday bo'lsa, shunday sobit bo'lishi va mavjudligi", 39),
            DictionaryWord("w2", "الأصل", "Asl", "Boshqa narsaning asosi va poydevori; o'ziga suyaniladigan narsa", 40),
            DictionaryWord("w3", "الفرع", "Far'", "Asl ustiga qurilgan va undan tarqalgan bo'lim yoki tarmoq", 41),
            DictionaryWord("w4", "الدليل", "Dalil", "Matlubga (maqsad qilingan to'g'ri ilmga) olib boruvchi sog'lom fikr va ko'rsatma", 42),
            DictionaryWord("w5", "الحجة", "Hujjat", "Qarshi tomonning asossiz bahsini daf etuvchi mustahkam va uzil-kesil dalil", 43),
            DictionaryWord("w6", "العلم", "Ilm", "Narsani o'zi qanday bo'lsa, shunday holatda aqlan qat'iy va shak-shubhasiz idrok etish", 44),
            DictionaryWord("w7", "الجهل", "Jahl", "Narsaning asl haqiqatiga zid bo'lgan noto'g'ri va xato e'tiqod", 45),
            DictionaryWord("w8", "الشك", "Shak", "Ikki zid ehtimol o'rtasida birortasini ustun qo'ya olmaslik, ikkilanib qolish", 46),
            DictionaryWord("w9", "الظن", "Zann", "Ikki ehtimoldan birini dalilga tayanib ustunroq va to'g'riroq deb hisoblash", 47),
            DictionaryWord("w10", "الوهم", "Vahm", "Kuchliroq va asosli ehtimolga qarshi bo'lgan zaif tasavvur", 48),
            DictionaryWord("w11", "القديم", "Qadim", "Boshlanishi va ibtidosi yo'q bo'lgan, azaldan mavjud Zot (faqat Alloh taolo)", 49),
            DictionaryWord("w12", "الحديث", "Hadis", "Avval yo'q bo'lib, so'ngra vujudga keltirilgan, yaratilgan har bir narsa", 50),
            DictionaryWord("w13", "الحدوث", "Hudus", "Yo'qlikdan borliqqa kelish, vaqt o'tishi bilan paydo bo'lish", 51),
            DictionaryWord("w14", "القدم", "Qidam", "Azaliylik, mavjudligining hech qanday boshlanish chegarasi yo'qligi", 52),
            DictionaryWord("w15", "الجوهر", "Javhar", "O'z-o'zicha qoyim bo'la oladigan, makonda o'rin egallovchi bo'linmas eng kichik zarra", 53),
            DictionaryWord("w16", "العرض", "Araz", "O'zicha mustaqil tura olmaydigan, faqat javhar yoki jismda zohir bo'ladigan xususiyat (rang, harakat, sukun)", 54),
            DictionaryWord("w17", "الجسم", "Jism", "Kamida ikki yoki undan ortiq javharning birikuvidan hosil bo'lgan uch o'lchamli tuzilma", 55),
            DictionaryWord("w18", "الوحدانية", "Vahdaniyat", "Alloh taoloning zotida, sifatlarida va fe'llarida sherigi yo'qligi, yagonaligi", 56),
            DictionaryWord("w19", "التنزيه", "Tanzih", "Alloh taoloni nuqson, kamchilik, maxluqotlarga o'xshashlik, jism va makondan mutlaq pok deb bilish", 57),
            DictionaryWord("w20", "التشبيه", "Tashbih", "Alloh taoloni yoki Uning sifatlarini maxluqotlarga o'xshatish (botil yo'l)", 58),
            DictionaryWord("w21", "التعطيل", "Ta'til", "Alloh taoloning azaliy sifatlarini inkor qilish va bekorga chiqarish", 59),
            DictionaryWord("w22", "الاستواء", "Istivo", "Alloh taoloning Arsh ustidan oliy qudrat va podshohlik hukmronligi (makonga muhtoj bo'lmagan holda)", 60),
            DictionaryWord("w23", "العرش", "Arsh", "Alloh taolo yaratgan eng ulug' va oliy jism, lekin Alloh unga hech ham muhtoj emas", 61),
            DictionaryWord("w24", "المكان", "Makon", "Moddiy jismlar joylashadigan o'rin; Alloh taolo makon va zamondan pokdir", 62),
            DictionaryWord("w25", "الصفة", "Sifat", "Zotga xos bo'lgan azaliy va abadiy ma'no (Ilm, Qudrat, Hayot va h.k.)", 63),
            DictionaryWord("w26", "التكوين", "Takvin", "Alloh taoloning yo'qdan bor qilish, yaratish azaliy sifati (Imom Moturidiy mazhabi)", 64),
            DictionaryWord("w27", "المكون", "Mukavvan", "Allohning takvin sifati bilan yaratilgan barcha mavjudotlar va maxluqotlar", 65),
            DictionaryWord("w28", "الكلام", "Kalom", "Alloh taoloning harf, tovush va sukutdan xoli bo'lgan azaliy so'zlash sifati", 66),
            DictionaryWord("w29", "الإرادة", "Iroda", "Mumkin bo'lgan har qanday narsani o'z vaqtida va belgilangan sifatda vujudga keltirishni tayin etuvchi sifat", 67),
            DictionaryWord("w30", "القدرة", "Qudrat", "Mumkin bo'lgan barcha narsalarni yo'qdan bor, bordan yo'q qilishga qodir azaliy sifat", 68),
            DictionaryWord("w31", "الحياة", "Hayot", "Ilm, qudrat va iroda sifatlarining to'g'ri bo'lishini taqozo etuvchi azaliy sifat", 69),
            DictionaryWord("w32", "السمع", "Sam'", "Barcha ovozlarni, pichirlash va sirlarni hech bir to'siqsiz eshitish sifati", 70),
            DictionaryWord("w33", "البصر", "Basar", "Barcha ko'rinadigan narsalarni, zulmatdagi harakatlarni ko'rish azaliy sifati", 71),
            DictionaryWord("w34", "الرؤية", "Ru'yat", "Oxiratda mo'minlarning Alloh taoloni jannatda kayfiyatsiz ko'rishi", 72),
            DictionaryWord("w35", "بلا كيف", "Bila kayf", "Shaklsiz, o'lchovsiz, o'xshatishsiz va chegarasiz ekani ifodasi", 73),
            DictionaryWord("w36", "الكسب", "Kasb", "Bandaning o'z ixtiyori va qudrati bilan fe'lga yuzlanishi; yaratuvchisi esa Allohdir", 74),
            DictionaryWord("w37", "الخلق", "Xalq", "Yo'qdan bor qilish; xoliqlik faqat Alloh taologagina xosdir", 75),
            DictionaryWord("w38", "الجبر", "Jabr", "Inson o'z amallarida mutlaqo majbur deb iddao qiluvchi botil aqida (Jabriya)", 76),
            DictionaryWord("w39", "القدر", "Qadar", "Narsalarning miqdori, o'lchovi va hikmat bilan belgilangan qonuniyati", 77),
            DictionaryWord("w40", "القضاء", "Qazo", "Belgilangan taqdir va hukmning o'z vaqtida voqe bo'lishi", 78),
            DictionaryWord("w41", "الرضا", "Rizo", "Alloh taoloning taqdir va qazosiga qalb xotirjamligi va taslimiyat bilan rozi bo'lish", 79),
            DictionaryWord("w42", "الإيمان", "Iymon", "Qalb bilan tasdiqlash va til bilan iqror bo'lish", 80),
            DictionaryWord("w43", "التصديq", "Tasdiq", "Payg'ambar keltirgan barcha diniy asoslarni yurakdan rost deb bilish", 81),
            DictionaryWord("w44", "الإقرار", "Iqror", "Qalbdagi iymonni til bilan aytib e'tirof qilish", 82),
            DictionaryWord("w45", "الكفر", "Kufr", "Haqiqatni berkitish, tavhidni yoki dindagi zaruriy aqidalarni inkor etish", 83),
            DictionaryWord("w46", "النفاق", "Nifoq", "Ichida kufrni saqlab, sirtida o'zini musulmon qilib ko'rsatish", 84),
            DictionaryWord("w47", "الشرك", "Shirk", "Alloh taologa zotida, sifatlarida yoki ibodatida sherik bor deb e'tiqod qilish", 85),
            DictionaryWord("w48", "الكبيرة", "Kabira", "Katta gunoh; u mo'minni dindan chiqarmaydi, balki fosiq qiladi", 86),
            DictionaryWord("w49", "الفاسق", "Fosiq", "Katta gunohni sodir etgan yoki kichik gunohda bardavom bo'lgan mo'min", 87),
            DictionaryWord("w50", "الشفاعة", "Shafoat", "Qiyomat kuni Payg'ambarimiz (s.a.v.)ning gunohkor mo'minlar uchun qiladigan mag'firat so'rovlari", 88),
            DictionaryWord("w51", "الميزان", "Mezon", "Qiyomat kuni insonlarning amallarini tortuvchi haqiqiy va adolatli tarozi", 89),
            DictionaryWord("w52", "الصراط", "Sirot", "Do'zax ustiga qurilgan, mo'minlar undan omon o'tib jannatga yetadigan ko'prik", 90),
            DictionaryWord("w53", "الحوض", "Havz", "Payg'ambarimiz (s.a.v.)ning mahshargohdagi Kavsar suvidan mo'minlarni to'yintiruvchi havzlari", 91),
            DictionaryWord("w54", "منكر ونكير", "Munkar va Nakir", "Qabrda inson dafn qilingach, Robbi, dini va payg'ambari haqida savol-javob qiluvchi ikki farishta", 92),
            DictionaryWord("w55", "البعث", "Ba's", "Qiyomat qoim bo'lgach, barcha vafot etganlarning qabrlardan hisob-kitob uchun qayta tirilishi", 93),
            DictionaryWord("w56", "النبوة", "Nubuwwat", "Alloh taoloning O'z bandalariga hidoyat yo'lini ko'rsatish uchun elchi yuborish vazifasi", 94),
            DictionaryWord("w57", "المعجزة", "Mo''jiza", "Payg'ambarlik da'vosini tasdiqlash uchun Alloh tomonidan ko'rsatilgan g'ayritabiiy, tengsiz hodisa", 95),
            DictionaryWord("w58", "الكرامة", "Karomat", "Taqvodor, solih avliyo bandalardan zohir bo'ladigan ilohiy ne'mat va holat", 96),
            DictionaryWord("w59", "الإمامة", "Imomat", "Musulmonlar jamoasini diniy va dunyoviy boshqarish, adolat o'rnatish rahbariyati", 97),
            DictionaryWord("w60", "الفطرة", "Fitrat", "Insonning yaratilishidagi tavhidga moyil, toza va pokiza tabiat", 98)
        )
        dictionaryDao.insertWords(initialWords)
    }

    // Backward compatibility helpers
    suspend fun askAiUstoz(question: String): Pair<String, List<String>> {
        return TamhidKnowledgeBase.askAiUstoz(question)
    }
}
