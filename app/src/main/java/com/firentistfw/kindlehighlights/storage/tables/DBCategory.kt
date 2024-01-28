package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("categories")
data class DBCategory(
    @PrimaryKey val categoryId: UUID,
    val date: Long,
    val name: String,
)