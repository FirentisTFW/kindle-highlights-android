package com.firentistfw.kindlehighlights.models

import java.util.*

data class Category(
    val date: String,
    val id: UUID = UUID.randomUUID(),
    val name: String,
)
