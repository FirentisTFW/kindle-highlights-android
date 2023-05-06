package com.firentistfw.kindlehighlights.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Highlight(
    val book: Book,
    val categories: List<Category>,
    val content: String,
    val date: String,
    val id: UUID = UUID.randomUUID(),
    val note: String? = null,
) : Parcelable
