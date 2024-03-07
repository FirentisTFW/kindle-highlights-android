package com.firentistfw.kindlehighlights.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImportedHighlight(
    val book: ImportedBook,
    val categories: List<Category>,
    val content: String,
    val date: String,
    val note: String? = null,
) : Parcelable
