package com.example.data.local

import com.example.data.model.*

object TamhidKnowledgeBase {
    val course: MadrasaCourse by lazy {
        val allChapters = mutableListOf<Chapter>()
        allChapters.addAll(TamhidChapters1To5.getChapters())
        allChapters.addAll(TamhidChapters6To10.getChapters())
        allChapters.addAll(TamhidChapters11To15.getChapters())
        allChapters.addAll(TamhidChapters16To20.getChapters())
        allChapters.addAll(TamhidChapters21To26.getChapters())

        MadrasaCourse(
            chapters = allChapters
        )
    }

    fun getAllChapters(): List<Chapter> = course.chapters

    fun getChapter(chapterId: String): Chapter? = course.chapters.find { it.id == chapterId }

    fun getLesson(lessonId: String): Lesson? {
        for (ch in course.chapters) {
            for (les in ch.lessons) {
                if (les.id == lessonId) return les
            }
        }
        return null
    }

    fun getAllFlashcards(): List<FlashcardItem> {
        val flashcards = mutableListOf<FlashcardItem>()
        course.chapters.forEach { ch ->
            ch.lessons.forEach { les ->
                les.concepts.forEach { concept ->
                    flashcards.add(
                        FlashcardItem(
                            id = "fc_${concept.id}",
                            lessonId = les.id,
                            chapterId = ch.id,
                            termUz = concept.termUz,
                            termAr = concept.termAr,
                            definitionUz = concept.definitionUz,
                            sourceQuote = concept.sourceExample,
                            pageRef = concept.pageRef
                        )
                    )
                }
            }
        }
        return flashcards
    }

    fun getAllQuizzes(): List<QuizQuestion> {
        val questions = mutableListOf<QuizQuestion>()
        course.chapters.forEach { ch ->
            ch.lessons.forEach { les ->
                questions.addAll(les.quizQuestions)
            }
        }
        return questions
    }

    data class SearchResult(
        val type: String, // "LESSON", "CONCEPT", "PROOF", "PARAGRAPH", "DEBATE"
        val titleUz: String,
        val titleAr: String,
        val snippetUz: String,
        val pageNumber: Int,
        val lessonId: String,
        val chapterNumber: Int
    )

    fun search(query: String): List<SearchResult> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        val results = mutableListOf<SearchResult>()

        course.chapters.forEach { ch ->
            ch.lessons.forEach { les ->
                // Check lesson title
                if (les.titleUz.lowercase().contains(q) || les.titleAr.contains(query) || les.summaryUz.lowercase().contains(q)) {
                    results.add(
                        SearchResult(
                            type = "LESSON",
                            titleUz = les.titleUz,
                            titleAr = les.titleAr,
                            snippetUz = les.summaryUz,
                            pageNumber = les.pdfStartPage,
                            lessonId = les.id,
                            chapterNumber = ch.chapterNumber
                        )
                    )
                }

                // Check concepts
                les.concepts.forEach { c ->
                    if (c.termUz.lowercase().contains(q) || c.termAr.contains(query) || c.definitionUz.lowercase().contains(q)) {
                        results.add(
                            SearchResult(
                                type = "CONCEPT",
                                titleUz = c.termUz,
                                titleAr = c.termAr,
                                snippetUz = c.definitionUz,
                                pageNumber = c.pageRef,
                                lessonId = les.id,
                                chapterNumber = ch.chapterNumber
                            )
                        )
                    }
                }

                // Check proofs
                les.proofs.forEach { p ->
                    if (p.titleUz.lowercase().contains(q) || p.arabicText.contains(query) || p.uzbekTranslation.lowercase().contains(q)) {
                        results.add(
                            SearchResult(
                                type = "PROOF",
                                titleUz = p.titleUz,
                                titleAr = p.arabicText.take(50),
                                snippetUz = p.uzbekTranslation,
                                pageNumber = les.pdfStartPage,
                                lessonId = les.id,
                                chapterNumber = ch.chapterNumber
                            )
                        )
                    }
                }

                // Check paragraphs
                les.paragraphs.forEach { para ->
                    if (para.uzbekTranslation.lowercase().contains(q) || para.arabicText.contains(query)) {
                        results.add(
                            SearchResult(
                                type = "PARAGRAPH",
                                titleUz = "${ch.chapterNumber}-bob, ${para.paragraphNumber}-fasl matni",
                                titleAr = para.arabicText.take(45) + "...",
                                snippetUz = para.uzbekTranslation.take(160) + "...",
                                pageNumber = para.pageNumber,
                                lessonId = les.id,
                                chapterNumber = ch.chapterNumber
                            )
                        )
                    }
                }

                // Check debates
                les.debates.forEach { d ->
                    if (d.topicTitle.lowercase().contains(q) || d.opposingSchool.lowercase().contains(q) || d.refutationUz.lowercase().contains(q)) {
                        results.add(
                            SearchResult(
                                type = "DEBATE",
                                titleUz = "${d.topicTitle} (Raddiya: ${d.opposingSchool})",
                                titleAr = d.ahlSunnahView.take(40),
                                snippetUz = d.refutationUz,
                                pageNumber = les.pdfStartPage,
                                lessonId = les.id,
                                chapterNumber = ch.chapterNumber
                            )
                        )
                    }
                }
            }
        }

        return results.distinctBy { "${it.type}_${it.titleUz}_${it.pageNumber}" }.take(30)
    }

    // Smart source-only query solver for Madrasa AI Ustoz
    fun askAiUstoz(question: String): Pair<String, List<String>> {
        val q = question.lowercase()
        val sources = mutableListOf<String>()

        val answerBuilder = StringBuilder()

        when {
            q.contains("haqiqat") || q.contains("sofist") || q.contains("safsata") -> {
                answerBuilder.append("«At-Tamhid» kitobi 1-bobida (39-41 betlar) Imom al-Lomishiy narsalarning haqiqatlari borligini umumiy oqillar mazhabi deb uqtiradi. Safsataviylar (inkorchilar, shubhachilar va indilar)ning 'haqiqat yo'q' degan da'volariga javoban: agar inkorning o'zi haqiqat bo'lsa, demak haqiqat sobitdir; agar inkor haqiqat bo'lmasa, o'z da'volari botil bo'ladi, deb rad etiladi.")
                sources.add("1-bob: Haqoiq al-ashya (39-41 betlar, Fasl 1-5)")
            }
            q.contains("ilm") || q.contains("sabab") || q.contains("his") || q.contains("aql") || q.contains("xabar") -> {
                answerBuilder.append("Imom al-Lomishiy 2-bobda (41-45 betlar) maxluqlar uchun ilm hosil bo'lishining 3 ta qonuniy sababini keltiradi:\n1. Havoss as-salima (5 ta sog'lom sezgi a'zosi);\n2. Mustaqim aql (fikrlash va aqliy istidlol);\n3. Sodiq xabarlar (mo'jizali payg'ambar xabari va mutavotir xabar). Shaxsiy ilhom esa hamma uchun umumiy ilm manbai bo'lolmaydi.")
                sources.add("2-bob: Asbob al-ilm (41-45 betlar, Fasl 6-15)")
            }
            q.contains("tamonu") || q.contains("vahdaniyat") || q.contains("birlik") || q.contains("sherik") -> {
                answerBuilder.append("Imom al-Lomishiy 4-bobda (51-55 betlar) Allohning yagonaligini 'Tamonu' dalili' bilan isbotlaydi. Agar ikkita xudo bo'lsa, ularning irodalari to'qnashishi mumkin edi: biri tiriltirishni, biri o'ldirishni xohlaganda ikkovining ham xohishi bo'lmasa ojizlik, biri g'olib bo'lsa ikkinchisi ojiz bo'lib qoladi. Ojiz esa iloh bo'lolmaydi. Bu Qur'onning Anbiyo surasi 22-oyatiga asoslanadi.")
                sources.add("4-bob: Vahdaniyat as-Soni' (51-55 betlar, Fasl 31-35)")
            }
            q.contains("jism") || q.contains("arsh") || q.contains("istivo") || q.contains("makon") || q.contains("jihat") -> {
                answerBuilder.append("Imom al-Lomishiy 5 va 7-boblarda (55-65 betlar) Alloh taolo jism, javhar yoki a'roz emasligini, makon va jihatlardan munazzah ekanini uqtiradi. Makonga o'rnashish maxluqlik va o'zgaruvchanlik alomatidir. Qur'ondagi 'Istivo' esa qaror topish emas, balki butun olam ustidan qudrat va g'alaba (istilo) ma'nosidadir. Duo qilganda osmonga qarash esa osmon duoning qiblasi bo'lgani uchundir.")
                sources.add("5 va 7-boblar: Tanzih al-Jism va Tanzih al-Makan (55-65 betlar)")
            }
            q.contains("sifat") || q.contains("la huva") || q.contains("ism") -> {
                answerBuilder.append("Ahli Sunna val-Jamoa e'tiqodida Allohning Hayot, Ilm, Qudrat, Sam', Basar, Iroda, Kalom va Takvin sifatlari azaliydir. Bu sifatlar 'Lā huva va lā g'ayruh' (Zotining aynan o'zi ham emas, Zotidan ajralgan boshqa narsa ham emas) qoidasi asosida e'tirof etiladi (8-bob, 65-70 betlar).")
                sources.add("8-bob: Isbot as-Sifat val-Asma (65-70 betlar, Fasl 59-69)")
            }
            q.contains("quron") || q.contains("kalam") || q.contains("maxluq") -> {
                answerBuilder.append("Imom al-Lomishiy 9-bobda (70-74 betlar) Kalomulloh Alloh Zotida qoyim azaliy sifat bo'lib, harf va tovushlardan xoli ekanini, Qur'on maxluq emasligini qat'iy isbotlaydi. Mu'taziliylarning xalqi Qur'on haqidagi da'volari botildir.")
                sources.add("9-bob: Azaliyat Kalamullah (70-74 betlar, Fasl 70-74)")
            }
            q.contains("takvin") || q.contains("mukavvan") -> {
                answerBuilder.append("Moturidiyya kalomining muhim asosi: Takvin (yaratish) azaliy sifat bo'lib, yaratilgan narsa (mukavvan)dan o'zgadir (10-bob, 74-78 betlar).")
                sources.add("10-bob: At-Takvin ghayr al-mukawwan (74-78 betlar, Fasl 75-86)")
            }
            q.contains("ruya") || q.contains("korish") || q.contains("jannat") -> {
                answerBuilder.append("Imom al-Lomishiy 12-bobda (79-86 betlar) mo'minlarning jannatda Alloh taoloni ko'rishlari haqiqat ekanini, bunga Muso alayhissalom so'rovi, Qiyomat surasi 22-23 oyatlari hamda Hakim at-Termiziy keltirgan 21 nafar sahobaning mutavotir hadislari dalil bo'lishini bayon qiladi. Ko'rish jismoniy masofasiz va jihatsiz (bila kayfiyya) ro'y beradi.")
                sources.add("12-bob: Isbot Ru'yatillah (79-86 betlar, Fasl 90-104)")
            }
            q.contains("karomat") || q.contains("valiy") -> {
                answerBuilder.append("Avliyolarning karomatlari haqdir (Maryam onamiz rizqi, Osif ibn Barxiya, Umar r.a. va Soriya voqeasi). Valiy payg'ambarlik da'vo qilmaydi, uning karomati u ergashgan payg'ambarning mo'jizasini quvvatlaydi (14-bob, 90-92 betlar).")
                sources.add("14-bob: Karomat al-Awliya (90-92 betlar, Fasl 114-117)")
            }
            q.contains("kasb") || q.contains("fe'l") || q.contains("qilmish") || q.contains("jabriya") || q.contains("qadariya") -> {
                answerBuilder.append("Bandalarning ixtiyoriy fe'llari yaratish (xalq) jihatidan Allohga, qozonish (kasb) jihatidan bandaga tegishlidir (16-bob, 97-103 betlar). Bu bilan Jabriyaning majburligi ham, Qadariyaning 'banda o'zi yaratadi' degan shirkli qarashi ham rad etiladi.")
                sources.add("16-bob: Khalq af'al al-ibad (97-103 betlar, Fasl 127-140)")
            }
            q.contains("ajal") || q.contains("qotil") || q.contains("o'lim") -> {
                answerBuilder.append("O'ldirilgan odam o'zining yagona ajali bilan o'ladi. Alloh uning qachon va qanday o'lishini azalda bilgan. Qotil esa shariat man etgan harom ishni qasddan kasb qilgani uchun jazolanadi (18-bob, 105-106 betlar).")
                sources.add("18-bob: Ajal al-maqtul (105-106 betlar, Fasl 146-148)")
            }
            q.contains("rizq") || q.contains("harom") -> {
                answerBuilder.append("Harom ozuqa ham rizq hisoblanadi. Chunki rizq — jonzot oziqlanadigan barcha quvvatdir. Agar harom rizq bo'lmasa, umr bo'yi harom yegan odam Allohning rizqini yemay o'tgan bo'lib qolar edi (19-bob, 106-107 betlar).")
                sources.add("19-bob: Al-Arzaq (106-107 betlar, Fasl 149-153)")
            }
            q.contains("gunoh") || q.contains("kabira") || q.contains("fosiq") || q.contains("shafoat") -> {
                answerBuilder.append("Katta gunoh (kabira) qilgan musulmon iymondan chiqmaydi, osiy mo'min bo'ladi. U do'zaxda abadiy qolmaydi. Rasululloh s.a.v.: «Mening shafoatim ummatimdan bo'lgan katta gunoh egalari uchundir», deb marhamat qilganlar (24-bob, 125-144 betlar).")
                sources.add("24-bob: Va'id fussaq va Shafoat (125-144 betlar, Fasl 193-217)")
            }
            q.contains("iymon") || q.contains("islom") || q.contains("istisno") || q.contains("inshaalloh") -> {
                answerBuilder.append("Iymon — qalb bilan tasdiqlash va til bilan iqror bo'lishdir. Iymon va Islom bitta narsadir. Iymonda shak bilan 'InshaAlloh mo'minman' deb istisno qilish joiz emas, balki qat'iyat bilan 'Haqiqiy mo'minman' deyiladi (25-bob, 145-163 betlar).")
                sources.add("25-bob: Kitob al-Iyman (145-163 betlar, Fasl 218-245)")
            }
            q.contains("sahoba") || q.contains("xalifa") || q.contains("imomat") || q.contains("abu bakr") || q.contains("umar") || q.contains("ali") -> {
                answerBuilder.append("Imom saylash ummatga vojibdir. Rasulullohdan keyingi eng afzal xalifalar ketma-ketligi: Abu Bakr Siddiq, Umar ibn Xattob, Usmon ibn Affon va Ali ibn Abu Tolibdir (r.a.). Barcha sahobalarni yaxshilik bilan eslash vojibdir (26-bob, 163-172 betlar).")
                sources.add("26-bob: Al-Imama va Fazoil as-Sahoba (163-172 betlar, Fasl 246-259)")
            }
            else -> {
                answerBuilder.append("«At-Tamhid li-qavoidit-tavhid» asari Imom Abu as-Sano Mahmud ibn Zayd al-Lomishiy al-Moturidiy (XI-XII asr) tomonidan yozilgan bo'lib, 26 ta bobda Ahli Sunna val-Jamoa (Moturidiyya) aqidasi qoidalarini, dalillarini hamda muxolif firqalarga raddiyalarni to'liq o'z ichiga oladi. Savolingizni aniqroq tushuncha (masalan: 'ru'yatulloh', 'takvin', 'kasb', 'sifatlar', 'qabr azobi', 'iymon') bo'yicha bersangiz, asardan to'g'ridan-to'g'ri matn va dalillar keltirib beraman.")
                sources.add("Kitob at-Tamhid umumiy mundarijasi (1-262 betlar)")
            }
        }

        return Pair(answerBuilder.toString(), sources)
    }
}
