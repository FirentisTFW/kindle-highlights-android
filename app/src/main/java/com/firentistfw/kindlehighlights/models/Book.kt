package com.firentistfw.kindlehighlights.models

import java.util.*

data class Book(
    val author: String,
    val id: UUID = UUID.randomUUID(),
    val title: String,
)
