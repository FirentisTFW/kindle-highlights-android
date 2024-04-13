package com.firentistfw.kindlehighlights.data.repository

import android.content.Context
import android.content.SharedPreferences
import java.util.Date

class ImportDetailsRepository(private val context: Context) {
    companion object {
        private const val PREF_NAME = "import-details"
        private const val KEY_LAST_IMPORTED_HIGHLIGHT_DATE = "last-imported-highlight-date"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getLastImportedHighlightDate(): Date? {
        val timestamp = sharedPreferences.getLong(KEY_LAST_IMPORTED_HIGHLIGHT_DATE, 0L)
        if (timestamp == 0L) return null
        return Date(timestamp)
    }

    fun storeLastImportedHighlightDate(date: Date) {
        sharedPreferences.edit().putLong(KEY_LAST_IMPORTED_HIGHLIGHT_DATE, date.time).apply()
    }
}