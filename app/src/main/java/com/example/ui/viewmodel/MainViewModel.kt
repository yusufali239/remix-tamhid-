package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.*
import com.example.data.model.*
import com.example.ui.theme.ReadingThemeMode
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)
    private val dataStoreManager = DataStoreManager(application)

    // Course data
    val course: MadrasaCourse = repository.getCourse()
    val allChapters: List<Chapter> = repository.getAllChapters()

    // Navigation & active state
    // Top-level destinations: [Darslik, Flashcards, AI Ustoz, Profil]
    private val _currentScreen = MutableStateFlow("darslik")
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

    private val _selectedChapter = MutableStateFlow<Chapter?>(allChapters.firstOrNull())
    val selectedChapter: StateFlow<Chapter?> = _selectedChapter.asStateFlow()

    private val _selectedLesson = MutableStateFlow<Lesson?>(allChapters.firstOrNull()?.lessons?.firstOrNull())
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson.asStateFlow()

    // Room v2 Data Streams
    val allBookmarks: StateFlow<List<Bookmark>> = repository.getAllBookmarks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<Note>> = repository.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allReadingProgress: StateFlow<List<ReadingProgress>> = repository.getAllReadingProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allFlashcardProgress: StateFlow<List<FlashcardProgress>> = repository.getAllFlashcardProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dueFlashcardsCount: StateFlow<Int> = repository.countDueFlashcards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val allQuizResults: StateFlow<List<QuizResultEntity>> = repository.getAllQuizResults()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // DataStore Preferences for Reader & App
    val appThemeMode: StateFlow<AppThemeMode> = dataStoreManager.appThemeModeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppThemeMode.AUTO)

    val lastBackupTime: StateFlow<Long?> = dataStoreManager.lastBackupTimeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val readerThemeMode: StateFlow<ReadingThemeMode> = dataStoreManager.themeModeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ReadingThemeMode.LIGHT)

    val fontSizeScale: StateFlow<Float> = dataStoreManager.fontSizeScaleFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1.0f)

    val showArabicDefault: StateFlow<Boolean> = dataStoreManager.showArabicDefaultFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val studentName: StateFlow<String> = dataStoreManager.studentNameFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Tolib")

    val userAge: StateFlow<Int> = dataStoreManager.userAgeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 20)

    val isOnboardingDone: StateFlow<Boolean> = dataStoreManager.isOnboardingDoneFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val reminderEnabled: StateFlow<Boolean> = dataStoreManager.reminderEnabledFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val reminderHour: StateFlow<Int> = dataStoreManager.reminderHourFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 20)

    val reminderMinute: StateFlow<Int> = dataStoreManager.reminderMinuteFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Lug'atlar bo'limi holati
    val dictionarySearchQuery = MutableStateFlow("")

    val allDictionaryWords: StateFlow<List<DictionaryWord>> = dictionarySearchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) repository.getAllDictionaryWords()
            else repository.searchDictionaryWords(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Streak and Daily Activity
    val activityDates: StateFlow<List<String>> = repository.getAllActivityDates()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentStreak: StateFlow<Int> = activityDates.map { dates ->
        calculateStreak(dates)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val maxStreak: StateFlow<Int> = activityDates.map { dates ->
        calculateMaxStreak(dates)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    init {
        viewModelScope.launch {
            repository.seedInitialDictionaryWordsIfEmpty()
        }
    }

    fun recordDailyActivity() {
        viewModelScope.launch {
            repository.recordTodayActivity()
        }
    }

    fun addDictionaryWord(arabic: String, uzbekTarjima: String, izoh: String, manbaBet: Int = 0) {
        viewModelScope.launch {
            val newWord = DictionaryWord(
                id = "custom_${System.currentTimeMillis()}",
                arabic = arabic.trim(),
                uzbekTarjima = uzbekTarjima.trim(),
                izoh = izoh.trim(),
                manbaBet = manbaBet,
                isCustom = true
            )
            repository.insertDictionaryWord(newWord)
        }
    }

    fun deleteDictionaryWord(word: DictionaryWord) {
        viewModelScope.launch {
            repository.deleteDictionaryWord(word)
        }
    }

    // Notion-style Bookmark selection & Search state
    private val _selectedBookmarkForDetail = MutableStateFlow<Bookmark?>(null)
    val selectedBookmarkForDetail: StateFlow<Bookmark?> = _selectedBookmarkForDetail.asStateFlow()

    val bookmarkSearchQuery = MutableStateFlow("")

    val filteredBookmarks: StateFlow<List<Bookmark>> = kotlinx.coroutines.flow.combine(
        allBookmarks,
        bookmarkSearchQuery
    ) { list, query ->
        if (query.isBlank()) list
        else list.filter {
            it.translation.contains(query, ignoreCase = true) ||
            it.arabicQuote.contains(query, ignoreCase = true) ||
            it.arabicFullText.contains(query, ignoreCase = true) ||
            it.userNote.contains(query, ignoreCase = true) ||
            it.chapterId.contains(query, ignoreCase = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun openBookmarkDetail(bookmark: Bookmark) {
        _selectedBookmarkForDetail.value = bookmark
        navigateTo("xatchop_detail")
    }

    fun updateBookmarkNote(id: String, note: String) {
        viewModelScope.launch {
            repository.updateBookmarkNote(id, note)
            val current = _selectedBookmarkForDetail.value
            if (current != null && current.id == id) {
                _selectedBookmarkForDetail.value = current.copy(userNote = note)
            }
        }
    }

    fun openBookmarkInReader(bookmark: Bookmark) {
        val chapter = allChapters.find { it.id == bookmark.chapterId || it.chapterNumber.toString() == bookmark.chapterId }
            ?: allChapters.firstOrNull()
        if (chapter != null) {
            selectChapter(chapter)
            val lesson = chapter.lessons.find { bookmark.pageNumber in it.pdfStartPage..it.pdfEndPage }
                ?: chapter.lessons.firstOrNull()
            if (lesson != null) {
                selectLesson(lesson)
            } else {
                navigateTo("reader")
            }
        }
    }

    fun openChapterParagraphInReader(chapterId: String, pageNumber: Int) {
        val chapter = allChapters.find { it.id == chapterId || it.chapterNumber.toString() == chapterId }
            ?: allChapters.firstOrNull()
        if (chapter != null) {
            selectChapter(chapter)
            val lesson = chapter.lessons.find { pageNumber in it.pdfStartPage..it.pdfEndPage }
                ?: chapter.lessons.firstOrNull()
            if (lesson != null) {
                selectLesson(lesson)
            } else {
                navigateTo("reader")
            }
        }
    }

    fun completeOnboarding(name: String, age: Int) {
        viewModelScope.launch {
            dataStoreManager.setUserProfile(name.trim().ifBlank { "Tolib" }, age.coerceIn(12, 80))
        }
    }

    fun updateUserAge(age: Int) {
        viewModelScope.launch {
            dataStoreManager.setUserAge(age.coerceIn(12, 80))
        }
    }

    // Active Flashcard Session State
    private val _activeFlashcards = MutableStateFlow<List<FlashcardItem>>(repository.getAllFlashcards())
    val activeFlashcards: StateFlow<List<FlashcardItem>> = _activeFlashcards.asStateFlow()

    private val _currentFlashcardIndex = MutableStateFlow(0)
    val currentFlashcardIndex: StateFlow<Int> = _currentFlashcardIndex.asStateFlow()

    private val _isFlashcardFlipped = MutableStateFlow(false)
    val isFlashcardFlipped: StateFlow<Boolean> = _isFlashcardFlipped.asStateFlow()

    // Navigation helper
    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }

    fun selectChapter(chapter: Chapter) {
        _selectedChapter.value = chapter
        _selectedLesson.value = chapter.lessons.firstOrNull()
    }

    fun selectLesson(lesson: Lesson) {
        _selectedLesson.value = lesson
        _selectedChapter.value = allChapters.find { it.id == lesson.chapterId }
        navigateTo("reader")
    }

    // Bookmark toggling (Room v2 / v3)
    fun toggleBookmark(
        chapterId: String,
        sectionId: String,
        arabicQuote: String,
        translation: String,
        pageNumber: Int,
        arabicFullText: String = arabicQuote,
        userNote: String = "",
        tags: String = ""
    ) {
        val id = if (sectionId.isNotBlank()) "${chapterId}_${sectionId}" else "p${pageNumber}_${chapterId}"
        viewModelScope.launch {
            repository.toggleBookmark(
                id = id,
                chapterId = chapterId,
                sectionId = sectionId,
                arabicQuote = arabicQuote,
                translation = translation,
                pageNumber = pageNumber,
                arabicFullText = arabicFullText,
                userNote = userNote,
                tags = tags
            )
        }
    }

    fun deleteBookmark(id: String) {
        viewModelScope.launch {
            repository.removeBookmark(id)
        }
    }

    // Notes
    fun addNote(chapterId: String, text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            repository.saveNote(chapterId, text)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    // Reading Progress
    fun updateReadingProgress(chapterId: String, percent: Int, lastPage: Int) {
        viewModelScope.launch {
            repository.saveReadingProgress(chapterId, percent, lastPage)
            repository.recordTodayActivity()
        }
    }

    // Flashcard Actions (Anki SuperMemo-2 SRS)
    fun startFlashcards(chapterId: String? = null) {
        val cards = if (chapterId != null) {
            repository.getAllFlashcards().filter { it.lessonId.startsWith(chapterId) }
        } else {
            repository.getAllFlashcards()
        }
        _activeFlashcards.value = cards.ifEmpty { repository.getAllFlashcards() }
        _currentFlashcardIndex.value = 0
        _isFlashcardFlipped.value = false
        navigateTo("flashcards")
    }

    fun flipFlashcard() {
        _isFlashcardFlipped.value = !_isFlashcardFlipped.value
    }

    fun answerFlashcardSrs(rating: SrsRating) {
        val currentCard = _activeFlashcards.value.getOrNull(_currentFlashcardIndex.value)
        if (currentCard != null) {
            viewModelScope.launch {
                repository.recordFlashcardReview(currentCard.id, rating)
                repository.recordTodayActivity()
            }
        }

        _isFlashcardFlipped.value = false
        if (_currentFlashcardIndex.value < _activeFlashcards.value.size - 1) {
            _currentFlashcardIndex.value += 1
        } else {
            _currentFlashcardIndex.value = 0 // loop
        }
    }

    // DataStore settings modifiers
    fun setAppThemeMode(mode: AppThemeMode) {
        viewModelScope.launch {
            dataStoreManager.setAppThemeMode(mode)
        }
    }

    fun setReaderTheme(mode: ReadingThemeMode) {
        viewModelScope.launch {
            dataStoreManager.setThemeMode(mode)
        }
    }

    fun setFontSizeScale(scale: Float) {
        viewModelScope.launch {
            dataStoreManager.setFontSizeScale(scale)
        }
    }

    fun setShowArabicDefault(show: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setShowArabicDefault(show)
        }
    }

    fun setStudentName(name: String) {
        viewModelScope.launch {
            dataStoreManager.setStudentName(name)
        }
    }

    // JSON Export & Import (Critical Data Safety)
    suspend fun exportDataJson(): String {
        val json = repository.exportUserDataToJson()
        dataStoreManager.setLastBackupTime(System.currentTimeMillis())
        return json
    }

    suspend fun importDataJson(jsonString: String): Boolean {
        val success = repository.importUserDataFromJson(jsonString)
        if (success) {
            dataStoreManager.setLastBackupTime(System.currentTimeMillis())
        }
        return success
    }

    // Active Quiz Session State
    private val _activeQuizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val activeQuizQuestions: StateFlow<List<QuizQuestion>> = _activeQuizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()

    private val _isQuizCompleted = MutableStateFlow(false)
    val isQuizCompleted: StateFlow<Boolean> = _isQuizCompleted.asStateFlow()

    fun startQuizForLesson(lessonOrChapterId: String) {
        val all = repository.getAllQuizzes()
        val questions = all.filter { it.lessonId == lessonOrChapterId || it.chapterId == lessonOrChapterId }
        val baseList = questions.ifEmpty { all.take(5) }
        val randomized = baseList.shuffled().map { q ->
            val correctOption = q.options.getOrElse(q.correctIndex) { q.options.first() }
            val shuffledOptions = q.options.shuffled()
            val newCorrectIndex = shuffledOptions.indexOf(correctOption).coerceAtLeast(0)
            q.copy(
                options = shuffledOptions,
                correctIndex = newCorrectIndex
            )
        }
        _activeQuizQuestions.value = randomized
        _currentQuestionIndex.value = 0
        _selectedAnswers.value = emptyMap()
        _isQuizCompleted.value = false
        navigateTo("quiz_session")
    }

    fun startGeneralQuiz(questionCount: Int = 20) {
        val all = repository.getAllQuizzes()
        val randomized = all.shuffled().take(questionCount).map { q ->
            val correctOption = q.options.getOrElse(q.correctIndex) { q.options.first() }
            val shuffledOptions = q.options.shuffled()
            val newCorrectIndex = shuffledOptions.indexOf(correctOption).coerceAtLeast(0)
            q.copy(
                options = shuffledOptions,
                correctIndex = newCorrectIndex
            )
        }
        _activeQuizQuestions.value = randomized
        _currentQuestionIndex.value = 0
        _selectedAnswers.value = emptyMap()
        _isQuizCompleted.value = false
        navigateTo("quiz_session")
    }

    fun selectQuizAnswer(questionIndex: Int, optionIndex: Int) {
        val currentMap = _selectedAnswers.value.toMutableMap()
        currentMap[questionIndex] = optionIndex
        _selectedAnswers.value = currentMap
    }

    fun nextQuizQuestion() {
        if (_currentQuestionIndex.value < _activeQuizQuestions.value.size - 1) {
            _currentQuestionIndex.value += 1
        } else {
            submitQuiz()
        }
    }

    fun previousQuizQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }

    private fun submitQuiz() {
        _isQuizCompleted.value = true
        val questions = _activeQuizQuestions.value
        val answers = _selectedAnswers.value

        var correct = 0
        val wrongIds = mutableListOf<String>()

        questions.forEachIndexed { index, q ->
            if (answers[index] == q.correctIndex) {
                correct++
            } else {
                wrongIds.add(q.id)
            }
        }

        viewModelScope.launch {
            repository.saveQuizResult(
                quizKey = questions.firstOrNull()?.lessonId ?: "GENERAL_EXAM",
                total = questions.size,
                correct = correct,
                wrongIds = wrongIds
            )
            repository.recordTodayActivity()
        }
    }

    private fun calculateStreak(dates: List<String>): Int {
        if (dates.isEmpty()) return 0
        val uniqueSorted = dates.toSet().sortedDescending()
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
        val todayStr = sdf.format(java.util.Date())

        // Check if latest date is today or yesterday
        val cal = java.util.Calendar.getInstance()
        cal.add(java.util.Calendar.DAY_OF_YEAR, -1)
        val yesterdayStr = sdf.format(cal.time)

        val latest = uniqueSorted.first()
        if (latest != todayStr && latest != yesterdayStr) {
            return 0
        }

        var streak = 0
        var currentCal = java.util.Calendar.getInstance()
        if (latest == yesterdayStr) {
            currentCal.add(java.util.Calendar.DAY_OF_YEAR, -1)
        }

        for (d in uniqueSorted) {
            val expectedStr = sdf.format(currentCal.time)
            if (d == expectedStr) {
                streak++
                currentCal.add(java.util.Calendar.DAY_OF_YEAR, -1)
            } else if (d < expectedStr) {
                break
            }
        }
        return streak
    }

    private fun calculateMaxStreak(dates: List<String>): Int {
        if (dates.isEmpty()) return 0
        val uniqueAsc = dates.toSet().sorted()
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
        var maxStreak = 1
        var currentStreak = 1

        val cal = java.util.Calendar.getInstance()
        for (i in 1 until uniqueAsc.size) {
            val prevDate = sdf.parse(uniqueAsc[i - 1]) ?: continue
            val currDate = sdf.parse(uniqueAsc[i]) ?: continue
            cal.time = prevDate
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1)

            if (sdf.format(cal.time) == sdf.format(currDate)) {
                currentStreak++
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak
                }
            } else {
                currentStreak = 1
            }
        }
        return maxOf(maxStreak, currentStreak)
    }

    fun setReminderSettings(enabled: Boolean, hour: Int, minute: Int) {
        viewModelScope.launch {
            dataStoreManager.setReminderSettings(enabled, hour, minute)
            com.example.data.worker.StudyReminderScheduler.scheduleDailyReminder(
                context = getApplication(),
                enabled = enabled,
                hour = hour,
                minute = minute
            )
        }
    }

    fun triggerTestReminder() {
        com.example.data.worker.StudyReminderScheduler.triggerImmediateTestReminder(getApplication())
    }
}
