package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight

@Dao
interface HighlightsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(highlight: DBHighlight)

    @Query("SELECT * FROM highlights")
    fun getAll(): List<DBHighlight>

    @Transaction
    @Query("SELECT * FROM highlights")
    // FIXME Rename
    fun getAllComplete(): List<CompleteHighlight>


    @Delete
    fun delete(highlight: DBHighlight)
}
