package com.firentistfw.kindlehighlights.extensions

fun Iterable<String>.filterNotEmpty(): List<String> = toMutableList().apply {
    this.removeAll(listOf(""))
}
