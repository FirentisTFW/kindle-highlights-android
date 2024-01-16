package com.firentistfw.kindlehighlights.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight

data class BookWithHighlights(
    @Embedded val book: DBBook,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val highlights: List<DBHighlight>
)