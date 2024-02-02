package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.models.Highlight
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
    private val selectionConditionsRepository: SelectionConditionsRepository,
) {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    suspend fun addHighlight(highlight: DBHighlight) = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.upsert(highlight)
    }

    suspend fun getAllHighlights(): List<CompleteHighlight> = withContext(Dispatchers.IO) {
        // FIXME What about mapping?
        return@withContext highlightsDao.getAllComplete()
    }

    suspend fun getDailyHighlights(): List<Highlight> {
        // FIXME First get data from cached daily source.

        repositoryScope.launch {

            val bookConditions = selectionConditionsRepository.getBookConditions()
            val categoryConditions = selectionConditionsRepository.getCategoryConditions()

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