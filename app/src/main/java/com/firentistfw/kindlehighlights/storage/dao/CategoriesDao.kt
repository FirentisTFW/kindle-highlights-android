package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firentistfw.kindlehighlights.storage.tables.DBCategory

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(book: DBCategory)

    @Query("SELECT * FROM categories")
    fun getAll(): List<DBCategory>
}
