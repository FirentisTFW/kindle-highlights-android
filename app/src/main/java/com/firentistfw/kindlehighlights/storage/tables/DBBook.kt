package com.firentistfw.kindlehighlights.storage.tables

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity("books")
data class DBBook(
    @PrimaryKey val bookId: UUID,
    val author: String,
    val title: String,
) : Parcelable

val DBBook.authorAndTitleDisplay: String
    get() = listOf(author, title).joinToString("\n")