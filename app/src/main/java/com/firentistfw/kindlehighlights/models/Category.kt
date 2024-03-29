package com.firentistfw.kindlehighlights.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Category(
    val date: String,
    val id: UUID = UUID.randomUUID(),
    val name: String,
) : Parcelable
