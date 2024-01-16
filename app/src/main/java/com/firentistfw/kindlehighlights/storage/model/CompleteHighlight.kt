package com.firentistfw.kindlehighlights.storage.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.storage.tables.HighlightCategoryCrossRef

data class CompleteHighlight(
    @Embedded val highlight: DBHighlight,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "bookId",
    )
    val book: DBBook,
    @Relation(
        parentColumn = "highlightId",
        entityColumn = "categoryId",
        associateBy = Junction(HighlightCategoryCrossRef::class)
    )
    val categories: List<DBCategory>,
)