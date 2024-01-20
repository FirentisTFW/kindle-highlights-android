package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.SelectionConditionsDao
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionConditionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SelectionConditionsRepository(private val conditionsDao: SelectionConditionsDao) {
    suspend fun addCondition(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext conditionsDao.upsert(condition)
    }

    // TODO Is this method needed?
    suspend fun getConditions(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext conditionsDao.getAll()
    }

    suspend fun getBookConditions(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext conditionsDao.getForConditionType(SelectionConditionType.Book)
    }

    suspend fun getCategoryConditions(): List<SelectionCondition> = withContext(Dispatchers.IO) {
        return@withContext conditionsDao.getForConditionType(SelectionConditionType.Category)
    }

    suspend fun removeCondition(condition: SelectionCondition) = withContext(Dispatchers.IO) {
        return@withContext conditionsDao.delete(condition)
    }
}