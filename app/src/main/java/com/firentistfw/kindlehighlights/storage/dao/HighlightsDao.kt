package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firentistfw.kindlehighlights.storage.model.DBHighlight

@Dao
interface HighlightsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(highlight: DBHighlight)

    @Query("SELECT * FROM highlights")
    fun getAll(): List<DBHighlight>

    @Delete
    fun delete(highlight: DBHighlight)
}
