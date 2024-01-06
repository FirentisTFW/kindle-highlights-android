package com.firentistfw.kindlehighlights.storage.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.firentistfw.kindlehighlights.models.Book
import com.firentistfw.kindlehighlights.models.Category

@Entity("highlights")
data class DBHighlight(
    @PrimaryKey val id: Int,
    @Embedded val book: Book,
    @Embedded val categories: List<Category>,
    val content: String,
    val date: String,
    val note: String?,
)
