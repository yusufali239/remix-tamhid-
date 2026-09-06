package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// ============================================================================
// TamhidDatabase Version 2
// Safe migration from v1 to v2 without data loss and strictly WITHOUT fallbackToDestructiveMigration
// ============================================================================

@Database(
    entities = [
        Bookmark::class,
        Note::class,
        FlashcardProgress::class,
        ReadingProgress::class,
        LessonProgressEntity::class,
        UserBookmarkEntity::class,
        UserFavoriteEntity::class,
        UserNoteEntity::class,
        QuizResultEntity::class,
        UserAppStateEntity::class,
        DictionaryWord::class,
        DailyActivityEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class TamhidDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun noteDao(): NoteDao
    abstract fun flashcardProgressDao(): FlashcardProgressDao
    abstract fun readingProgressDao(): ReadingProgressDao
    abstract fun progressDao(): ProgressDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun quizDao(): QuizDao
    abstract fun appStateDao(): AppStateDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun dailyActivityDao(): DailyActivityDao

    companion object {
        @Volatile
        private var INSTANCE: TamhidDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // 1. Create modern bookmarks table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `bookmarks` (
                        `id` TEXT NOT NULL,
                        `chapterId` TEXT NOT NULL,
                        `sectionId` TEXT NOT NULL,
                        `arabicQuote` TEXT NOT NULL,
                        `translation` TEXT NOT NULL,
                        `pageNumber` INTEGER NOT NULL,
                        `timestamp` INTEGER NOT NULL,
                        PRIMARY KEY(`id`)
                    )
                """.trimIndent())

                // 2. Create modern notes table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `notes` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `chapterId` TEXT NOT NULL,
                        `text` TEXT NOT NULL,
                        `timestamp` INTEGER NOT NULL
                    )
                """.trimIndent())

                // 3. Create reading_progress table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `reading_progress` (
                        `chapterId` TEXT NOT NULL,
                        `percent` INTEGER NOT NULL,
                        `lastPage` INTEGER NOT NULL,
                        PRIMARY KEY(`chapterId`)
                    )
                """.trimIndent())

                // 4. Update flashcard_progress to support SRS algorithm
                try {
                    db.execSQL("DROP TABLE IF EXISTS `flashcard_progress_old`")
                    db.execSQL("ALTER TABLE `flashcard_progress` RENAME TO `flashcard_progress_old`")
                } catch (e: Exception) {
                    // If old table did not exist
                }
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `flashcard_progress` (
                        `cardId` TEXT NOT NULL,
                        `level` INTEGER NOT NULL,
                        `nextReviewDate` INTEGER NOT NULL,
                        `easeFactor` REAL NOT NULL,
                        PRIMARY KEY(`cardId`)
                    )
                """.trimIndent())

                try {
                    db.execSQL("""
                        INSERT OR IGNORE INTO `flashcard_progress` (`cardId`, `level`, `nextReviewDate`, `easeFactor`)
                        SELECT flashcardId, timesRemembered, nextReviewTimestamp, 2.5
                        FROM `flashcard_progress_old`
                    """)
                    db.execSQL("DROP TABLE IF EXISTS `flashcard_progress_old`")
                } catch (e: Exception) {
                    // Ignore if flashcard_progress_old wasn't present
                }

                // Migrate existing user_bookmarks to bookmarks table
                try {
                    db.execSQL("""
                        INSERT OR IGNORE INTO `bookmarks` (`id`, `chapterId`, `sectionId`, `arabicQuote`, `translation`, `pageNumber`, `timestamp`)
                        SELECT 'p' || pageNumber || '_' || lessonId, lessonId, CAST(paragraphIndex AS TEXT), arabicSnippet, uzbekTitle, pageNumber, createdTimestamp
                        FROM `user_bookmarks`
                    """)
                } catch (e: Exception) {}

                // Migrate existing user_notes to notes table
                try {
                    db.execSQL("""
                        INSERT OR IGNORE INTO `notes` (`chapterId`, `text`, `timestamp`)
                        SELECT lessonId, noteText, createdTimestamp
                        FROM `user_notes`
                    """)
                } catch (e: Exception) {}
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE `bookmarks` ADD COLUMN `arabicFullText` TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE `bookmarks` ADD COLUMN `userNote` TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE `bookmarks` ADD COLUMN `tags` TEXT NOT NULL DEFAULT ''")
                db.execSQL("UPDATE `bookmarks` SET `arabicFullText` = `arabicQuote` WHERE `arabicFullText` = ''")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `dictionary_words` (
                        `id` TEXT NOT NULL,
                        `arabic` TEXT NOT NULL,
                        `uzbekTarjima` TEXT NOT NULL,
                        `izoh` TEXT NOT NULL,
                        `manbaBet` INTEGER NOT NULL DEFAULT 0,
                        `isCustom` INTEGER NOT NULL DEFAULT 0,
                        `timestamp` INTEGER NOT NULL DEFAULT 0,
                        PRIMARY KEY(`id`)
                    )
                """.trimIndent())
            }
        }

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `daily_activity` (
                        `date` TEXT NOT NULL,
                        `timestamp` INTEGER NOT NULL DEFAULT 0,
                        PRIMARY KEY(`date`)
                    )
                """.trimIndent())
            }
        }

        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                try {
                    // Update legacy bookmark IDs (p{pageNumber}_{chapterId}) to unique paragraph-based IDs ({chapterId}_{sectionId})
                    db.execSQL("""
                        UPDATE `bookmarks`
                        SET `id` = `chapterId` || '_' || `sectionId`
                        WHERE `id` LIKE 'p%_%' AND `sectionId` != ''
                    """.trimIndent())
                } catch (e: Exception) {
                    // Safe fallback if primary key conflict occurs with legacy duplicates
                }
            }
        }

        fun getDatabase(context: Context): TamhidDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TamhidDatabase::class.java,
                    "at_tamhid_madrasa_db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
                    // Strict constraint: NEVER use fallbackToDestructiveMigration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
