package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class HighlightsRepository(
    private val highlightsDao: HighlightsDao,
    private val selectionsRepository: SelectionsRepository,
) {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    suspend fun addHighlight(highlight: DBHighlight) = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.upsert(highlight)
    }

    suspend fun addHighlights(highlights: List<DBHighlight>) = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.insertAll(highlights)
    }

    suspend fun getAllHighlights(): List<CompleteHighlight> = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.getAllComplete()
    }

    suspend fun getDailyHighlights(): List<CompleteHighlight> {
        // FIXME First get data from cached daily source.

        repositoryScope.launch {

            val bookConditions = selectionsRepository.getBookConditions()
            val categoryConditions = selectionsRepository.getCategoryConditions()

            val categoryHighlights = highlightsDao.getForCategories(bookConditions.ids())
            val bookHighlights = highlightsDao.getForBooks(categoryConditions.ids())
            // FIXME Take randomly 5 elements
            val highlights = categoryHighlights + bookHighlights

        }

        delay(2000)

        return listOf(Mocks.exampleHighlight, Mocks.exampleHighlight2)
    }
}

private fun List<SelectionCondition>.ids(): List<UUID> {
    return map {
        it.id
    }
}