package com.firentistfw.kindlehighlights.data.repository

import android.content.Context
import android.content.SharedPreferences
import java.util.Date

class CachedDailyHighlightsRepository(private val context: Context) {
    companion object {
        private const val PREF_NAME = "cached-daily-highlights"
        private const val KEY_CACHE_DATE = "date"
        private const val KEY_HIGHLIGHTS_IDS = "highlights-ids"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getCacheDate(): Date {
        val timestamp = sharedPreferences.getLong(KEY_CACHE_DATE, 0L)
        return Date(timestamp)
    }

    fun getCachedHighlightsIds(): Set<String> {
        return sharedPreferences.getStringSet(KEY_HIGHLIGHTS_IDS, emptySet()) ?: emptySet()
    }

    fun storeHighlightsIds(ids: Set<String>, date: Date) {
        sharedPreferences.edit().putStringSet(KEY_HIGHLIGHTS_IDS, ids).apply()
        sharedPreferences.edit().putLong(KEY_CACHE_DATE, date.time).apply()
    }
}