package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

enum class SelectionType {
    Book,
    Category,
}

@Entity("selection-conditions")
data class SelectionCondition(
    @PrimaryKey val id: UUID,
    val type: SelectionType,

    /**
     * Either category id or book id.
     */
    val selectionId: UUID,
)
