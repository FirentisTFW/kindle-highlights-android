package com.firentistfw.kindlehighlights.common.mocks

import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import java.util.UUID

class Mocks {
    companion object {

        private val exampleBook = DBBook(
            bookId = UUID.randomUUID(),
            author = "Artour Rakhimov",
            title = "B00CAXAKAA",
        )

        val exampleHighlight =
            CompleteHighlight(
                book = exampleBook,
                categories = emptyList(),
                highlight = DBHighlight(
                    highlightId = UUID.randomUUID(),
                    bookId = exampleBook.bookId,
                    content = "Another option is to do physical exercise instead of breathing exercises. Warm conditions will increase your perspiration. As a result, you get something that is similar to hot yoga. This solution will produce results that are compatible to a breathing session in colder conditions. For many people, physical exercise in hot conditions, due to sweating, can be even a more effective way to increase their CP.h",
                    date = "May 06, 2023 10:03:07 PM",
                    note = "Workout in warm environment can be more beneficial than RB",
                ),
            )

        val exampleHighlight2 =
            CompleteHighlight(
                book = exampleBook,
                categories = emptyList(),
                highlight = DBHighlight(
                    highlightId = UUID.randomUUID(),
                    bookId = exampleBook.bookId,
                    content = "Warm conditions will increase your perspiration. As a result, you get something that is similar to hot yoga. This solution will produce results that are compatible to a breathing session in colder conditions. For many people, physical exercise in hot conditions, due to sweating, can be even a more effective way to increase their CP.h",
                    date = "May 06, 2023 10:06:07 PM",
                ),
            )
    }
}