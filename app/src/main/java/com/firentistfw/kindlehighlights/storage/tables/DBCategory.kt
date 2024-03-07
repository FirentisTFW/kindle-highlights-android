package com.firentistfw.kindlehighlights.storage.tables

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity("categories")
data class DBCategory(
    @PrimaryKey val categoryId: UUID,
    val date: Long,
    val name: String,
) : Parcelable