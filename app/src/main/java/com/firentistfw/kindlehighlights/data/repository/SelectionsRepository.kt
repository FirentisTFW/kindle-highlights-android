package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.SelectionsDao
import com.firentistfw.kindlehighlights.storage.model.BookSelection
import com.firentistfw.kindlehighlights.storage.model.CategorySelection
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionConditionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SelectionsRepository(private val selectionsDao: SelectionsDao) {
    suspend fun addCondition(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.upsert(condition)
    }

    suspend fun getBookConditions(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.getForConditionType(SelectionConditionType.Book)
    }

    fun getBookSelectionsFlow(): Flow<List<BookSelection>> {
        return selectionsDao.getBookSelectionsFlow()
    }

    suspend fun getCategoryConditions(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.getForConditionType(SelectionConditionType.Category)
    }

    fun getCategorySelectionsFlow(): Flow<List<CategorySelection>> {
        return selectionsDao.getCategorySelectionsFlow()
    }

    suspend fun removeCondition(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.delete(condition)
    }
}