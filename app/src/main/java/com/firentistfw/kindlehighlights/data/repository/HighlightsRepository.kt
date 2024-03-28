package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getDailyHighlights(count: Int): List<CompleteHighlight> {
        // FIXME First get data from cached daily source.
        return suspendCoroutine { continuation ->
            repositoryScope.launch {
                val bookSelections = selectionsRepository.getBookSelections()
                val categorySelections = selectionsRepository.getCategorySelections()

                val categoryHighlights = highlightsDao.getForCategories(categorySelections.selectionIds())
                val bookHighlights = highlightsDao.getForBooks(bookSelections.selectionIds())
                val allHighlights = categoryHighlights + bookHighlights
                val selectedHighlights = List(count)  {
                    allHighlights.random()
                }.toSet().toList()

                continuation.resume(selectedHighlights)
            }
        }
    }

    suspend fun getHighlightById(id: UUID): CompleteHighlight = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.getById(id)
    }

}

private fun List<SelectionCondition>.selectionIds(): List<UUID> {
    return map {
        it.selectionId
    }
}