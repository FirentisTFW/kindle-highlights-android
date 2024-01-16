package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "highlight-category-cross-ref", primaryKeys = ["categoryId", "highlightId"])
data class HighlightCategoryCrossRef(
    val categoryId: UUID,
    val highlightId: UUID,
)