package com.firentistfw.kindlehighlights.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition

data class CategorySelection(
    @Embedded
    val condition: SelectionCondition,
    @Relation(
        parentColumn = "selectionId",
        entityColumn = "categoryId",
    )
    val category: DBCategory,
)