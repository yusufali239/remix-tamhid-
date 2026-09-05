package com.example.data.worker

import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

object StudyReminderScheduler {
    const val WORK_TAG = "study_reminder_work"
    const val TEST_TAG = "test_reminder_tag"

    fun scheduleDailyReminder(context: Context, enabled: Boolean, hour: Int, minute: Int) {
        val workManager = WorkManager.getInstance(context)
        if (!enabled) {
            workManager.cancelAllWorkByTag(WORK_TAG)
            return
        }

        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(now)) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        val initialDelay = (target.timeInMillis - now.timeInMillis).coerceAtLeast(0L)

        val workRequest = PeriodicWorkRequestBuilder<StudyReminderWorker>(
            24, TimeUnit.HOURS,
            15, TimeUnit.MINUTES
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .addTag(WORK_TAG)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_TAG,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun triggerImmediateTestReminder(context: Context) {
        val workManager = WorkManager.getInstance(context)
        val testRequest = OneTimeWorkRequestBuilder<StudyReminderWorker>()
            .addTag(TEST_TAG)
            .build()
        workManager.enqueue(testRequest)
    }
}
