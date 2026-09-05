package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.ui.theme.ReadingThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class AppThemeMode {
    AUTO, LIGHT, DARK
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tamhid_user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        val KEY_THEME_MODE = stringPreferencesKey("reader_theme_mode")
        val KEY_APP_THEME_MODE = stringPreferencesKey("app_theme_mode")
        val KEY_LAST_BACKUP_TIME = androidx.datastore.preferences.core.longPreferencesKey("last_backup_timestamp")
        val KEY_FONT_SIZE_SCALE = floatPreferencesKey("reader_font_size_scale")
        val KEY_SHOW_ARABIC_ORIGINAL = booleanPreferencesKey("reader_show_arabic_original")
        val KEY_STUDENT_NAME = stringPreferencesKey("student_name")
        val KEY_USER_NAME = stringPreferencesKey("user_name")
        val KEY_USER_AGE = androidx.datastore.preferences.core.intPreferencesKey("user_age")
        val KEY_ONBOARDING_DONE = booleanPreferencesKey("is_onboarding_done")
        val KEY_REMINDER_ENABLED = booleanPreferencesKey("reminder_enabled")
        val KEY_REMINDER_HOUR = androidx.datastore.preferences.core.intPreferencesKey("reminder_hour")
        val KEY_REMINDER_MINUTE = androidx.datastore.preferences.core.intPreferencesKey("reminder_minute")
    }

    val appThemeModeFlow: Flow<AppThemeMode> = context.dataStore.data.map { preferences ->
        val modeStr = preferences[KEY_APP_THEME_MODE] ?: AppThemeMode.AUTO.name
        try {
            AppThemeMode.valueOf(modeStr)
        } catch (e: Exception) {
            AppThemeMode.AUTO
        }
    }

    val lastBackupTimeFlow: Flow<Long?> = context.dataStore.data.map { preferences ->
        preferences[KEY_LAST_BACKUP_TIME]
    }

    val themeModeFlow: Flow<ReadingThemeMode> = context.dataStore.data.map { preferences ->
        val modeStr = preferences[KEY_THEME_MODE] ?: ReadingThemeMode.LIGHT.name
        try {
            ReadingThemeMode.valueOf(modeStr)
        } catch (e: Exception) {
            ReadingThemeMode.LIGHT
        }
    }

    val fontSizeScaleFlow: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[KEY_FONT_SIZE_SCALE] ?: 1.0f
    }

    val showArabicDefaultFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_SHOW_ARABIC_ORIGINAL] ?: false
    }

    val studentNameFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[KEY_USER_NAME] ?: preferences[KEY_STUDENT_NAME] ?: "Tolib"
    }

    val userAgeFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_USER_AGE] ?: 20
    }

    val isOnboardingDoneFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_ONBOARDING_DONE] ?: false
    }

    val reminderEnabledFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_REMINDER_ENABLED] ?: false
    }

    val reminderHourFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_REMINDER_HOUR] ?: 20
    }

    val reminderMinuteFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_REMINDER_MINUTE] ?: 0
    }

    suspend fun setThemeMode(mode: ReadingThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[KEY_THEME_MODE] = mode.name
        }
    }

    suspend fun setAppThemeMode(mode: AppThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[KEY_APP_THEME_MODE] = mode.name
        }
    }

    suspend fun setLastBackupTime(timestamp: Long) {
        context.dataStore.edit { preferences ->
            preferences[KEY_LAST_BACKUP_TIME] = timestamp
        }
    }

    suspend fun setFontSizeScale(scale: Float) {
        context.dataStore.edit { preferences ->
            preferences[KEY_FONT_SIZE_SCALE] = scale
        }
    }

    suspend fun setShowArabicDefault(show: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_SHOW_ARABIC_ORIGINAL] = show
        }
    }

    suspend fun setStudentName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_STUDENT_NAME] = name
            preferences[KEY_USER_NAME] = name
        }
    }

    suspend fun setUserProfile(name: String, age: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_NAME] = name
            preferences[KEY_STUDENT_NAME] = name
            preferences[KEY_USER_AGE] = age
            preferences[KEY_ONBOARDING_DONE] = true
        }
    }

    suspend fun setOnboardingDone(done: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_ONBOARDING_DONE] = done
        }
    }

    suspend fun setUserAge(age: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_AGE] = age
        }
    }

    suspend fun setReminderSettings(enabled: Boolean, hour: Int, minute: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_REMINDER_ENABLED] = enabled
            preferences[KEY_REMINDER_HOUR] = hour
            preferences[KEY_REMINDER_MINUTE] = minute
        }
    }
}
