package com.firentistfw.kindlehighlights.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Book(
    val author: String,
    val id: UUID = UUID.randomUUID(),
    val title: String,
) : Parcelable

val Book.authorAndTitleDisplay: String
    get() = listOf(author, title).joinToString("\n")