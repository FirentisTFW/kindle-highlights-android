package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import java.util.UUID

@Dao
interface HighlightsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(highlight: DBHighlight)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(highlights: List<DBHighlight>)

    @Query("SELECT * FROM highlights")
    suspend fun getAll(): List<DBHighlight>

    @Transaction
    @Query("SELECT * FROM highlights")
    // FIXME Rename
    suspend fun getAllComplete(): List<CompleteHighlight>

    @Transaction
    @Query(
        "SELECT * FROM highlights " +
                "INNER JOIN books on highlights.bookId = books.bookId " +
                "WHERE books.bookId IN (:bookIds)"
    )
    suspend fun getForBooks(bookIds: List<UUID>): List<CompleteHighlight>

    @Transaction
    @Query(
        "SELECT * FROM highlights " +
                "INNER JOIN `highlight-category-cross-ref` crossRef on highlights.highlightId = crossRef.highlightId " +
                "INNER JOIN categories on crossRef.categoryId = categories.categoryId " +
                "   WHERE categories.categoryId IN (:categoryIds)"
    )
    suspend fun getForCategories(categoryIds: List<UUID>): List<CompleteHighlight>

    @Delete
    suspend fun delete(highlight: DBHighlight)
}
