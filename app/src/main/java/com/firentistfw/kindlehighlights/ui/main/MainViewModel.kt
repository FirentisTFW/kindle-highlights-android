package com.firentistfw.kindlehighlights.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.data.repository.BooksRepository
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.data.repository.LocalFilesRepository
import com.firentistfw.kindlehighlights.models.ImportedHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.utils.KindleClippingsParser
import kotlinx.coroutines.launch
import java.util.UUID

class MainViewModel(
    private val localFilesRepository: LocalFilesRepository,
    private val highlightsRepository: HighlightsRepository,
    private val booksRepository: BooksRepository,
    private val clippingsParser: KindleClippingsParser,
) : BaseViewModel() {
    private val _fileImportRequestState = MutableLiveData<RequestState<Nothing>>()
    val fileImportRequestState: LiveData<RequestState<Nothing>> get() = _fileImportRequestState

    fun importHighlightsFromFile(uri: Uri) {
        _fileImportRequestState.value = RequestState.Ongoing()

        viewModelScope.launch {
            try {
                val content = localFilesRepository.readFileContent(uri)
                val importedHighlights =
                    clippingsParser.createHighlightsFromRawClippingsInput(content)

                val books = importedHighlights.map(ImportedHighlight::book).toSet().map {
                    DBBook(
                        bookId = UUID.randomUUID(),
                        author = it.author,
                        title = it.title,
                    )
                }

                val highlights = importedHighlights.map { importedHighlight ->
                    val book = books.first {
                        it.author == importedHighlight.book.author &&
                                it.title == importedHighlight.book.title
                    }
                    importedHighlight.mapToHighlight(book.bookId)
                }

                booksRepository.addBooks(books)
                highlightsRepository.addHighlights(highlights)

                _fileImportRequestState.value = RequestState.Success()
            } catch (e: Exception) {
                _fileImportRequestState.value = RequestState.Error(e.message)
            }
        }
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
