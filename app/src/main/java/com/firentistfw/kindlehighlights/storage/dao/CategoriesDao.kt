package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(book: DBCategory)

    @Query("SELECT * FROM categories")
    fun getAll(): List<DBCategory>

    @Query(
        "SELECT * FROM categories " +
                "WHERE categories.categoryId IN " +
                "(SELECT categoryId FROM `highlight-category-cross-ref` " +
                "WHERE highlightId = :highlightId)"
    )
    fun getFlowForHighlight(highlightId: UUID): Flow<List<DBCategory>>

    @Query(
        "SELECT * FROM categories " +
                "WHERE categories.categoryId NOT IN " +
                "(SELECT categoryId FROM `highlight-category-cross-ref` " +
                "WHERE highlightId = :highlightId)"
    )
    fun getFlowNotForHighlight(highlightId: UUID): Flow<List<DBCategory>>

    @Transaction
    @Query(
        "SELECT * FROM categories " +
                "WHERE categoryId NOT IN " +
                "(SELECT selectionId FROM `selection-conditions` " +
                "WHERE type LIKE 'Category')"
    )
    fun getNotSelectedFlow(): Flow<List<DBCategory>>
}
