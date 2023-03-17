package com.firentistfw.kindlehighlights.extensions

fun Iterable<String>.filterNotEmpty(): List<String> = toMutableList().apply {
    this.removeAll(listOf(""))
}

fun String.removeParenthesis(): String = replace(Regex("[()]"), "")

fun String.substringFrom(text: String, shouldIncludeStart: Boolean = true): String =
    substring(indexOf(text)).let {
        if (!shouldIncludeStart) it.removeRange(0, 1) else it
    }

fun String.substringUntil(text: String): String = substring(0, indexOf(text))
