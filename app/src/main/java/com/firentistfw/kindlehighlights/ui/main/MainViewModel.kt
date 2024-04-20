package com.firentistfw.kindlehighlights.ui.main

import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.data.repository.BooksRepository
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.data.repository.ImportDetailsRepository
import com.firentistfw.kindlehighlights.data.repository.LocalFilesRepository
import com.firentistfw.kindlehighlights.models.ImportedHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.utils.KindleClippingsParser
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import java.util.UUID

class MainViewModel(
    private val localFilesRepository: LocalFilesRepository,
    private val highlightsRepository: HighlightsRepository,
    private val importDetailsRepository: ImportDetailsRepository,
    private val booksRepository: BooksRepository,
    private val clippingsParser: KindleClippingsParser,
) : BaseViewModel() {
    private val _fileImportRequestState = MutableLiveData<RequestState<Nothing>>()
    val fileImportRequestState: LiveData<RequestState<Nothing>> get() = _fileImportRequestState

    private val dateFormatter = DateFormatter()

    fun importHighlightsFromFile(uri: Uri) {
        _fileImportRequestState.value = RequestState.Ongoing()

        viewModelScope.launch {
            try {
                val content = localFilesRepository.readFileContent(uri)
                val importedHighlights =
                    clippingsParser.createHighlightsFromRawClippingsInput(content)

                val books = importedHighlights.mapToBooks()
                val highlights = importedHighlights.mapToHighlights(books)

                val newHighlights = getNewHighlights(highlights)
                val newBooks = getNewBooks(books.take(4))

                booksRepository.addBooks(newBooks)
                highlightsRepository.addHighlights(newHighlights)

                val lastHighlight = highlights.lastOrNull()
                if (lastHighlight != null) storeLastImportedHighlightDate(lastHighlight)

                _fileImportRequestState.value = RequestState.Success()
            } catch (e: Exception) {
                _fileImportRequestState.value = RequestState.Error(e.message)
            }
        }
    }

    private fun getNewHighlights(highlights: List<DBHighlight>): List<DBHighlight> {
        val lastImportedHighlightDate =
            importDetailsRepository.getLastImportedHighlightDate() ?: return highlights
        val lastImportedHighlightIndex = highlights.indexOfLast {
            dateFormatter.convertHighlightStringDateToDate(it.date) == lastImportedHighlightDate
        }

        return highlights.subList(lastImportedHighlightIndex + 1, highlights.size)
    }

    private suspend fun getNewBooks(books: List<DBBook>): List<DBBook> {
        val importedBooks = booksRepository.getBooks()
        return books.filter { book ->
            val bookAlreadyImported = importedBooks.any { importedBook ->
                importedBook.author == book.author && importedBook.title == book.title
            }
            !bookAlreadyImported
        }
    }

    private fun storeLastImportedHighlightDate(highlight: DBHighlight) {
        val date = dateFormatter.convertHighlightStringDateToDate(highlight.date)
        importDetailsRepository.storeLastImportedHighlightDate(date)
    }

    private class DateFormatter {
        private val dateFormatter = SimpleDateFormat("MMMM d, y h:mm:s a", Locale.US)

        fun convertHighlightStringDateToDate(highlightDate: String): Date {
            return dateFormatter.parse(highlightDate)
        }
    }
}

private fun List<ImportedHighlight>.mapToBooks(): List<DBBook> {
    return map(ImportedHighlight::book).toSet().map {
        DBBook(
            bookId = UUID.randomUUID(),
            author = it.author,
            title = it.title,
        )
    }
}

private fun List<ImportedHighlight>.mapToHighlights(books: List<DBBook>): List<DBHighlight> {
    return map { importedHighlight ->
        val book = books.first {
            it.author == importedHighlight.book.author && it.title == importedHighlight.book.title
        }
        importedHighlight.mapToHighlight(book.bookId)
    }
}

private fun ImportedHighlight.mapToHighlight(bookId: UUID): DBHighlight {
    return DBHighlight(
        highlightId = UUID.randomUUID(),
        bookId = bookId,
        content = content,
        date = date,
        note = note,
    )
}

