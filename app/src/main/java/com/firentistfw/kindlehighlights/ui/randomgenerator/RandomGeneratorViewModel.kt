package com.firentistfw.kindlehighlights.ui.randomgenerator

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.BooksRepository
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class RandomGeneratorViewModel(
    private val booksRepository: BooksRepository,
    private val categoriesRepository: CategoriesRepository,
    private val highlightsRepository: HighlightsRepository,
) : BaseViewModel() {
    private val generator = Generator()

    fun addRandomBook() {
        val book = generator.generateRandomBook()

        viewModelScope.launch {
            booksRepository.addBook(book)
        }
    }

    fun addRandomHighlight() {
        viewModelScope.launch {
            val book = booksRepository.getBooks().random()
            val highlight = generator.generateRandomHighlight(book.bookId)

            highlightsRepository.addHighlight(highlight)
        }
    }

    fun addRandomCategory() {
        val category = generator.generateRandomCategory()

        viewModelScope.launch {
            categoriesRepository.addCategory(category)
        }
    }
}

private class Generator {
    fun generateRandomBook(): DBBook {
        val author = "${firstNames.random()} ${lastNames.random()}"
        val title = "${words.random()} ${words.random()}"

        return DBBook(
            bookId = UUID.randomUUID(),
            author = author,
            title = title,
        )
    }

    fun generateRandomHighlight(bookId: UUID): DBHighlight {
        val highlightLength = (10..50).random()
        val content = List(highlightLength) {
            words.random()
        }.joinToString(" ")

        return DBHighlight(
            bookId = bookId,
            highlightId = UUID.randomUUID(),
            content = content,
            date = Date().toString(),
        )
    }

    fun generateRandomCategory(): DBCategory {
        return DBCategory(
            categoryId = UUID.randomUUID(),
            date = Date().time,
            name = words.random(),
        )
    }

    private val firstNames =
        arrayOf("Eva", "Liam", "Olivia", "Noah", "Emma", "Sophia", "Isaac", "Mia", "Lucas", "Ava")

    private val lastNames = arrayOf(
        "Smith",
        "Johnson",
        "Williams",
        "Jones",
        "Brown",
        "Davis",
        "Miller",
        "Wilson",
        "Moore",
        "Taylor"
    )

    private val words = arrayOf(
        "Apple",
        "Mountain",
        "Guitar",
        "Ocean",
        "Sunshine",
        "Elephant",
        "Chocolate",
        "Rainbow",
        "Happiness",
        "Adventure",
        "Stellar",
        "Whisper",
        "Blossom",
        "Serenity",
        "Harmony",
        "Enchanted",
        "Vibrant",
        "Courage",
        "Infinity",
        "Mystical"
    )
}