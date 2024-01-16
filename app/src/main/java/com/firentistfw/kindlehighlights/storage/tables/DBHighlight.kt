package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("highlights")
data class DBHighlight(
    @PrimaryKey val highlightId: UUID,
    val bookId: UUID,
    val content: String,
    val date: String,
    val note: String? = null,
)
