package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.extensions.getUniqueRandomElements
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.utils.AppDateUtils
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HighlightsRepository(
    private val highlightsDao: HighlightsDao,
    private val selectionsRepository: SelectionsRepository,
    private val cachedDailyHighlightsRepository: CachedDailyHighlightsRepository,
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

    suspend fun getDailyHighlights(count: Int, currentDate: Date): List<CompleteHighlight> {
        return suspendCoroutine { continuation ->
            repositoryScope.launch {
                if (hasCachedHighlightsForDay(currentDate)) {
                    val cachedHighlights = getCachedDailyHighlights(count)
                    if (cachedHighlights.size < count) {
                        val freshHighlights = getFreshDailyHighlights(count - cachedHighlights.size)
                        val allHighlights = mutableListOf<CompleteHighlight>().apply {
                            addAll(cachedHighlights)
                            addAll(freshHighlights)
                        }
                        cacheHighlights(allHighlights, currentDate)
                        continuation.resume(allHighlights)
                    } else {
                        continuation.resume(cachedHighlights)
                    }
                } else {
                    val freshHighlights = getFreshDailyHighlights(count)
                    cacheHighlights(freshHighlights, currentDate)
                    continuation.resume(freshHighlights)
                }

            }

        }
    }

    suspend fun getHighlightById(id: UUID): CompleteHighlight = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.getById(id)
    }

    suspend fun deleteHighlight(highlight: DBHighlight) = withContext(Dispatchers.IO) {
        return@withContext highlightsDao.delete(highlight)
    }

    private fun hasCachedHighlightsForDay(date: Date): Boolean {
        val cachedHighlightsDate = cachedDailyHighlightsRepository.getCacheDate()
        return AppDateUtils.isSameDay(date, cachedHighlightsDate)
    }

    private suspend fun getFreshDailyHighlights(count: Int): List<CompleteHighlight> {
        return suspendCoroutine { continuation ->
            repositoryScope.launch {
                val bookSelections = selectionsRepository.getBookSelections()
                val categorySelections = selectionsRepository.getCategorySelections()

                val categoryHighlights =
                    highlightsDao.getForCategories(categorySelections.selectionIds())
                val bookHighlights = highlightsDao.getForBooks(bookSelections.selectionIds())
                val allHighlights = mutableListOf<CompleteHighlight>().apply {
                    addAll(categoryHighlights)
                    addAll(bookHighlights)
                }

                val highlights = allHighlights.getUniqueRandomElements(count)

                continuation.resume(highlights)
            }
        }
    }

    private suspend fun getCachedDailyHighlights(count: Int): List<CompleteHighlight> {
        val deferred = CompletableDeferred<List<CompleteHighlight>>()
        repositoryScope.launch {
            val cachedHighlightsIds = cachedDailyHighlightsRepository.getCachedHighlightsIds()

            val highlights = cachedHighlightsIds.map {
                async { highlightsDao.getById(UUID.fromString(it)) }
            }.awaitAll().take(count)

            deferred.complete(highlights)
        }

        return deferred.await()
    }

    private fun cacheHighlights(
        highlights: List<CompleteHighlight>,
        currentDate: Date,
    ) {
        val ids = highlights.map { it.highlight.highlightId.toString() }.toSet()
        cachedDailyHighlightsRepository.storeHighlightsIds(ids, currentDate)
    }
}

private fun List<SelectionCondition>.selectionIds(): List<UUID> = map { it.selectionId }