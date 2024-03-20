package com.firentistfw.kindlehighlights.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition

data class BookSelection (
    @Embedded
    val condition: SelectionCondition,
    @Relation(
        parentColumn = "selectionId",
        entityColumn = "bookId",
    )
    val book: DBBook,
)