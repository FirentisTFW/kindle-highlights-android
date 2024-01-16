package com.firentistfw.kindlehighlights.data.repository

import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.models.Highlight
import com.firentistfw.kindlehighlights.storage.dao.BooksDao
import com.firentistfw.kindlehighlights.storage.dao.HighlightsDao
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class HighlightsRepository(private val dao: HighlightsDao, private val booksDao: BooksDao) {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    fun allHighlights(): List<Highlight> {
        return emptyList()

//        return dao.getAll().map {
//            Mapper.dbHighlightToHighlight(it)
//        }
    }

    suspend fun dailyHighlights(): List<Highlight> {
        // FIXME Implement
        // Get data from cached daily source. If it's empty, then get it from database and cache for this day.

        repositoryScope.launch {

            dao.upsert(
                DBHighlight(
                    id = UUID.fromString("1f46d17d-9e3a-4842-9634-a7f9fd55400f"),
                    bookId = UUID.fromString("1cc65b77-9964-4516-b769-82b182246756"),
                    content = "Test",
                    date = "Test",
                    note = "Test",
                ),
            )


            dao.upsert(
                DBHighlight(
                    id = UUID.fromString("4f95451d-1bdb-4304-a168-0f2bf59f54d8"),
                    bookId = UUID.fromString("1cc65b77-9964-4516-b769-82b182246756"),
                    content = "Test2",
                    date = "Test2",
                    note = "Test2",
                ),
            )



            val books = booksDao.getAllWithHighlights()

            println(books)
        }



        delay(2000)

        return listOf(Mocks.exampleHighlight, Mocks.exampleHighlight2)
    }
}

private object Mapper {
//    fun dbHighlightToHighlight(highlight: DBHighlight): Highlight {
//        return Highlight(
//            book = highlight.book,
//            categories = highlight.categories,
//            content = highlight.content,
//            date = highlight.date,
//            id = highlight.id,
//            note = highlight.note,
//        )
//    }
}
