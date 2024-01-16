package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.firentistfw.kindlehighlights.models.Category
import java.util.UUID

@Entity("highlights")
data class DBHighlight(
    @PrimaryKey val id: UUID,
    val bookId: UUID,
    val content: String,
    val date: String,
    val note: String? = null,
)
