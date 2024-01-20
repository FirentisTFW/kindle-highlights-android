package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition

@Dao
interface SelectionConditionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(condition: SelectionCondition)

    @Query("SELECT * FROM 'selection-conditions'")
    fun getAll(): List<SelectionCondition>

    @Delete
    fun delete(condition: SelectionCondition)
}