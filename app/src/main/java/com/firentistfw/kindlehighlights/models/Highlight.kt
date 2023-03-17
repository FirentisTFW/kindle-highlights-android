package com.firentistfw.kindlehighlights.models

import java.util.*

data class Highlight(
    val book: Book,
    val categories: List<Category>,
    val content: String,
    val date: String,
    val id: UUID = UUID.randomUUID(),
    val note: String? = null,
)
