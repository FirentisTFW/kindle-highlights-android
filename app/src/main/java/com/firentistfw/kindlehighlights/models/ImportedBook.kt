package com.firentistfw.kindlehighlights.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImportedBook(
    val author: String,
    val title: String,
) : Parcelable
