package com.firentistfw.kindlehighlights.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.firentistfw.kindlehighlights.storage.model.BookWithHighlights
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(book: DBBook)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(books: List<DBBook>)

    @Query("SELECT * FROM books")
    fun getAll(): List<DBBook>

    @Transaction
    @Query("SELECT * FROM books")
    fun getAllWithHighlights(): List<BookWithHighlights>

    @Transaction
    @Query("SELECT * FROM books " +
            "WHERE bookId NOT IN " +
            "(SELECT selectionId FROM `selection-conditions` " +
            "WHERE type LIKE 'Book')")
    fun getNotSelectedFlow(): Flow<List<DBBook>>
}
