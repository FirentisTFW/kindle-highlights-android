package com.firentistfw.kindlehighlights.storage.tables

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity("highlights")
data class DBHighlight(
    @PrimaryKey val highlightId: UUID,
    val bookId: UUID,
    val content: String,
    val date: String,
    val note: String? = null,
) : Parcelable
