package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// ============================================================================
// Clean Minimalism 2.0 Room Entities
// Guaranteed Data Persistence for Bookmarks, Notes, SRS Flashcards, and Reading Progress
// ============================================================================

@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey val id: String, // format: "p{pageNumber}_{chapterId}"
    val chapterId: String,
    val sectionId: String,
    val arabicQuote: String,
    val translation: String,
    val pageNumber: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val arabicFullText: String = arabicQuote,
    val userNote: String = "",
    val tags: String = ""
)

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapterId: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "flashcard_progress")
data class FlashcardProgress(
    @PrimaryKey val cardId: String,
    val level: Int = 0,
    val nextReviewDate: Long = 0L,
    val easeFactor: Float = 2.5f
)

@Entity(tableName = "reading_progress")
data class ReadingProgress(
    @PrimaryKey val chapterId: String,
    val percent: Int = 0,
    val lastPage: Int = 1
)

// Backwards-compatibility and auxiliary entities
@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val isCompleted: Boolean = false,
    val progressPercentage: Int = 0,
    val lastParagraphIndex: Int = 0,
    val lastUpdatedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_bookmarks")
data class UserBookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val paragraphIndex: Int,
    val arabicSnippet: String,
    val uzbekTitle: String,
    val pageNumber: Int,
    val createdTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_favorites")
data class UserFavoriteEntity(
    @PrimaryKey val favoriteKey: String,
    val itemType: String,
    val itemId: String,
    val titleUz: String,
    val titleAr: String,
    val snippetUz: String,
    val pageNumber: Int,
    val createdTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_notes")
data class UserNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val noteText: String,
    val selectedTextRef: String? = null,
    val createdTimestamp: Long = System.currentTimeMillis(),
    val updatedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val quizKey: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Int,
    val wrongQuestionIdsCsv: String = "",
    val completedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_app_state")
data class UserAppStateEntity(
    @PrimaryKey val key: String,
    val value: String
)

@Entity(tableName = "dictionary_words")
data class DictionaryWord(
    @PrimaryKey val id: String,
    val arabic: String,
    val uzbekTarjima: String,
    val izoh: String,
    val manbaBet: Int = 0,
    val isCustom: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "daily_activity")
data class DailyActivityEntity(
    @PrimaryKey val date: String, // Format: YYYY-MM-DD
    val timestamp: Long = System.currentTimeMillis()
)

