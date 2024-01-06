package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.data.datasource.highlights.HighlightsDataSource
import com.firentistfw.kindlehighlights.models.Highlight
import kotlinx.coroutines.delay

class HighlightsRepository(private val dataSource: HighlightsDataSource) {
    fun allHighlights(): List<Highlight> {
        // FIXME Implement

        return listOf<Highlight>()
    }

    suspend fun dailyHighlights(): List<Highlight> {
        // FIXME Implement
        // Get data from cached daily source. If it's empty, then get it from database and cache for this day.

        delay(2000)

        return listOf(Mocks.exampleHighlight, Mocks.exampleHighlight2)
    }
}