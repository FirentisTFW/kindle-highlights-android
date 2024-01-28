package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.CategoriesDao
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesRepository(private val categoriesDao: CategoriesDao) {
    suspend fun addCategory(category: DBCategory) = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.upsert(category)
    }

    suspend fun getCategories(): List<DBCategory> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.getAll()
    }
}