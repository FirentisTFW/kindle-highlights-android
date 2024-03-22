package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.firentistfw.kindlehighlights.storage.model.BookSelection
import com.firentistfw.kindlehighlights.storage.model.CategorySelection
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionType
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(condition: SelectionCondition)

    @Query("SELECT * FROM 'selection-conditions'")
    fun getAll(): List<SelectionCondition>

    @Query("SELECT * FROM 'selection-conditions' WHERE type LIKE :type")
    fun getForConditionType(type: SelectionType): List<SelectionCondition>

    @Transaction
    @Query(
        "SELECT * FROM 'selection-conditions' " +
                "INNER JOIN books on selectionId = books.bookId " +
                "WHERE type LIKE 'Book'"
    )
    fun getBookSelectionsFlow(): Flow<List<BookSelection>>

    @Transaction
    @Query(
        "SELECT * FROM 'selection-conditions' " +
                "INNER JOIN categories on selectionId = categories.categoryId " +
                "WHERE type LIKE 'Category'"
    )
    fun getCategorySelectionsFlow(): Flow<List<CategorySelection>>

    @Delete
    fun delete(condition: SelectionCondition)
}