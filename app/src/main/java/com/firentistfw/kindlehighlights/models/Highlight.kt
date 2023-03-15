package com.firentistfw.kindlehighlights.models

data class Highlight(
    val book: Book,
    val categories: List<Category>,
    val content: String,
    val date: String,
    val id: String,
    val note: String?,
)
