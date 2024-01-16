package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("categories")
data class DBCategory(
    @PrimaryKey val id: UUID,
    val date: String,
    val name: String,
)