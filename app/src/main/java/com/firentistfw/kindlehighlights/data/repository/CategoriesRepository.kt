package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.CategoriesDao
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID

class CategoriesRepository(private val categoriesDao: CategoriesDao) {
    suspend fun addCategory(category: DBCategory) = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.upsert(category)
    }

    suspend fun getCategories(): List<DBCategory> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.getAll()
    }

    fun getAssignedCategoriesFlowForHighlight(highlightId: UUID): Flow<List<DBCategory>> {
        return categoriesDao.getFlowForHighlight(highlightId)
    }

    fun getPossibleCategoriesFlowForHighlight(highlightId: UUID): Flow<List<DBCategory>> {
        return categoriesDao.getFlowNotForHighlight(highlightId)
    }
}