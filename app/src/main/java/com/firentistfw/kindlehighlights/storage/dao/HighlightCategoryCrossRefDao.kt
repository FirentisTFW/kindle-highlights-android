package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.firentistfw.kindlehighlights.storage.tables.HighlightCategoryCrossRef

@Dao
interface HighlightCategoryCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(crossRef: HighlightCategoryCrossRef)
}