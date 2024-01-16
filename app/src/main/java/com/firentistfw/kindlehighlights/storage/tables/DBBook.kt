package com.firentistfw.kindlehighlights.storage.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("books")
data class DBBook(
    @PrimaryKey val id: UUID,
    val author: String,
    val title: String,
)
