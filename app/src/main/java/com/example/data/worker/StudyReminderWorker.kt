package com.example.data.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.MainActivity
import com.example.R
import com.example.data.local.AiProvider
import com.example.data.local.DataStoreManager
import com.example.data.local.TamhidDatabase
import kotlinx.coroutines.flow.first

class StudyReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val CHANNEL_ID = "study_reminders_channel"
        const val NOTIFICATION_ID = 1001

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Mutolaa eslatmalari"
                val descriptionText = "Kundalik aqida darslarini o'rganish eslatmalari"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    override suspend fun doWork(): Result {
        val context = applicationContext

        // 1. Check if reminders are enabled in DataStore
        val dataStoreManager = DataStoreManager(context)
        val isEnabled = dataStoreManager.reminderEnabledFlow.first()
        val isTestRun = tags.contains(StudyReminderScheduler.TEST_TAG)
        if (!isEnabled && !isTestRun) {
            return Result.success()
        }

        // 2. Check permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return Result.success()
            }
        }

        // 3. Query real user progress from Room DB
        val db = TamhidDatabase.getDatabase(context)
        val readingProgressList = try {
            db.readingProgressDao().getAllReadingProgress().first()
        } catch (_: Exception) {
            emptyList()
        }

        var currentChapterNum = 1
        var sumPercent = 0
        for (p in readingProgressList) {
            sumPercent += p.percent
            val num = p.chapterId.removePrefix("ch_").toIntOrNull()
            if (num != null && p.percent > 0) {
                if (num > currentChapterNum) {
                    currentChapterNum = num
                }
            }
        }
        val overallPercent = (sumPercent / 26).coerceIn(0, 100)

        val dueCards = try {
            db.flashcardProgressDao().countDueCards(System.currentTimeMillis()).first()
        } catch (_: Exception) {
            0
        }

        val studentName = try {
            dataStoreManager.studentNameFlow.first()
        } catch (_: Exception) {
            "Tolib"
        }

        // 4. Generate text using AI (Gemini/Groq) with guaranteed fallback strings
        val reminderText = AiProvider.generateStudyReminder(
            studentName = studentName,
            currentChapter = currentChapterNum.coerceIn(1, 26),
            progressPercent = overallPercent,
            dueCards = dueCards
        )

        // 5. Send notification
        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("At-Tamhid • Mutolaa eslatmasi")
            .setContentText(reminderText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(reminderText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        } catch (_: SecurityException) {
            // Permission denied or revoked
        }

        return Result.success()
    }
}
