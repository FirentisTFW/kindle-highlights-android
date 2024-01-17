package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

enum class SelectionConditionType {
    BOOK,
    CATEGORY,
}

@Entity("selection-conditions")
data class SelectionCondition(
    @PrimaryKey val id: UUID,
    val type: SelectionConditionType,

    /**
     * Either category id or book id.
     */
    val selectionId: UUID,
)
