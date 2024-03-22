package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.SelectionsDao
import com.firentistfw.kindlehighlights.storage.model.BookSelection
import com.firentistfw.kindlehighlights.storage.model.CategorySelection
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SelectionsRepository(private val selectionsDao: SelectionsDao) {
    suspend fun addSelection(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.upsert(condition)
    }

    suspend fun getBookSelections(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.getForConditionType(SelectionType.Book)
    }

    fun getBookSelectionsFlow(): Flow<List<BookSelection>> {
        return selectionsDao.getBookSelectionsFlow()
    }

    suspend fun getCategorySelections(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.getForConditionType(SelectionType.Category)
    }

    fun getCategorySelectionsFlow(): Flow<List<CategorySelection>> {
        return selectionsDao.getCategorySelectionsFlow()
    }

    suspend fun removeSelection(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext selectionsDao.delete(condition)
    }
}