package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// Modern Clean Minimal Room DAOs

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<Bookmark>>

    @Query("SELECT * FROM bookmarks WHERE chapterId = :chapterId")
    fun getBookmarksForChapter(chapterId: String): Flow<List<Bookmark>>

    @Query("SELECT * FROM bookmarks WHERE id = :id LIMIT 1")
    suspend fun getBookmarkById(id: String): Bookmark?

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmarkById(id: String)

    @Query("UPDATE bookmarks SET userNote = :note WHERE id = :id")
    suspend fun updateBookmarkNote(id: String, note: String)

    @Query("SELECT * FROM bookmarks WHERE translation LIKE '%' || :query || '%' OR arabicFullText LIKE '%' || :query || '%' OR arabicQuote LIKE '%' || :query || '%' OR userNote LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchBookmarks(query: String): Flow<List<Bookmark>>
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE chapterId = :chapterId ORDER BY timestamp DESC")
    fun getNotesForChapter(chapterId: String): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Long): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Long)
}

@Dao
interface FlashcardProgressDao {
    @Query("SELECT * FROM flashcard_progress")
    fun getAllProgress(): Flow<List<FlashcardProgress>>

    @Query("SELECT * FROM flashcard_progress WHERE cardId = :cardId LIMIT 1")
    suspend fun getProgressForCard(cardId: String): FlashcardProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: FlashcardProgress)

    @Query("SELECT COUNT(*) FROM flashcard_progress WHERE nextReviewDate <= :currentTime")
    fun countDueCards(currentTime: Long): Flow<Int>
}

@Dao
interface ReadingProgressDao {
    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgress(): Flow<List<ReadingProgress>>

    @Query("SELECT * FROM reading_progress WHERE chapterId = :chapterId LIMIT 1")
    suspend fun getProgressForChapter(chapterId: String): ReadingProgress?

    @Query("SELECT * FROM reading_progress WHERE chapterId = :chapterId LIMIT 1")
    fun observeProgressForChapter(chapterId: String): Flow<ReadingProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgress)
}

// Compatibility DAOs
@Dao
interface ProgressDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId")
    suspend fun getProgressForLesson(lessonId: String): LessonProgressEntity?

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId")
    fun observeProgressForLesson(lessonId: String): Flow<LessonProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: LessonProgressEntity)

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE isCompleted = 1")
    fun getCompletedLessonsCount(): Flow<Int>
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM user_favorites ORDER BY createdTimestamp DESC")
    fun getAllFavorites(): Flow<List<UserFavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM user_favorites WHERE favoriteKey = :key)")
    fun isFavorite(key: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: UserFavoriteEntity)

    @Query("DELETE FROM user_favorites WHERE favoriteKey = :key")
    suspend fun deleteFavorite(key: String)
}

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_results ORDER BY completedTimestamp DESC")
    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    @Query("SELECT * FROM quiz_results WHERE quizKey = :quizKey ORDER BY scorePercentage DESC LIMIT 1")
    suspend fun getBestResultForQuiz(quizKey: String): QuizResultEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuizResult(result: QuizResultEntity): Long

    @Query("SELECT COUNT(*) FROM quiz_results")
    fun getTotalQuizzesTaken(): Flow<Int>
}

@Dao
interface AppStateDao {
    @Query("SELECT value FROM user_app_state WHERE `key` = :key")
    suspend fun getValue(key: String): String?

    @Query("SELECT value FROM user_app_state WHERE `key` = :key")
    fun observeValue(key: String): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setValue(state: UserAppStateEntity)
}

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary_words ORDER BY uzbekTarjima COLLATE NOCASE ASC")
    fun getAllWords(): Flow<List<DictionaryWord>>

    @Query("SELECT * FROM dictionary_words WHERE uzbekTarjima LIKE '%' || :query || '%' OR arabic LIKE '%' || :query || '%' OR izoh LIKE '%' || :query || '%' ORDER BY uzbekTarjima COLLATE NOCASE ASC")
    fun searchWords(query: String): Flow<List<DictionaryWord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: DictionaryWord)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWords(words: List<DictionaryWord>)

    @Delete
    suspend fun deleteWord(word: DictionaryWord)

    @Query("SELECT COUNT(*) FROM dictionary_words")
    suspend fun getCount(): Int
}

@Dao
interface DailyActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun recordActivity(activity: DailyActivityEntity)

    @Query("SELECT date FROM daily_activity ORDER BY date DESC")
    fun getAllActivityDates(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM daily_activity WHERE date = :date")
    suspend fun hasActivityOnDate(date: String): Int
}

