package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.BooksDao
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(private val booksDao: BooksDao) {
    suspend fun addBook(book: DBBook) = withContext(Dispatchers.IO) {
        return@withContext booksDao.upsert(book)
    }

    suspend fun addBooks(books: List<DBBook>) = withContext(Dispatchers.IO) {
        return@withContext booksDao.insertAll(books)
    }

    suspend fun getBooks(): List<DBBook> = withContext(Dispatchers.IO) {
        return@withContext booksDao.getAll()
    }
}