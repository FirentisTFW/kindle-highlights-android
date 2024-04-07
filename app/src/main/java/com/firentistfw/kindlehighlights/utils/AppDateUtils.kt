package com.firentistfw.kindlehighlights.utils

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppDateUtils {
    fun isSameDay(date1: Date, date2: Date): Boolean {
        // TODO: Handle different locales
        val formatter = SimpleDateFormat("yyyyMMdd", Locale("pl"))
        return formatter.format(date1).equals(formatter.format(date2))
    }
}