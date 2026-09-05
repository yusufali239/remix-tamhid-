package com.example.data.local

import android.content.Context

/**
 * AppDatabase alias and delegator to TamhidDatabase to maintain backward compatibility.
 */
typealias AppDatabase = TamhidDatabase

object AppDatabaseHelper {
    fun getDatabase(context: Context): TamhidDatabase = TamhidDatabase.getDatabase(context)
}
