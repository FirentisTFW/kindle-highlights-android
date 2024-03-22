package com.firentistfw.kindlehighlights.ui.selections

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.BooksRepository
import com.firentistfw.kindlehighlights.data.repository.SelectionsRepository
import com.firentistfw.kindlehighlights.storage.model.BookSelection
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ManageBookSelectionsViewModel(
    private val booksRepository: BooksRepository,
    private val selectionsRepository: SelectionsRepository,
) : BaseViewModel() {
    private val _bookSelections = MutableStateFlow<List<BookSelection>>(emptyList())
    val bookSelections: StateFlow<List<BookSelection>> get() = _bookSelections.asStateFlow()

    private val _otherBooks = MutableStateFlow<List<DBBook>>(emptyList())
    val otherBooks: StateFlow<List<DBBook>> get() = _otherBooks.asStateFlow()

    fun fetchBooksAndSelections() {
        viewModelScope.launch {
            selectionsRepository.getBookSelectionsFlow().collect {
                _bookSelections.value = it
            }
        }

        viewModelScope.launch {
            booksRepository.getNotSelectedBooksFlow().collect {
                _otherBooks.value = it
            }
        }
    }

    fun selectBook(bookId: UUID) {
        viewModelScope.launch {
            selectionsRepository.addSelection(
                SelectionCondition(
                    id = UUID.randomUUID(),
                    type = SelectionType.Book,
                    selectionId = bookId,
                )
            )
        }
    }

    fun unselectBook(bookId: UUID) {
        val selection = bookSelections.value.first {
            it.book.bookId == bookId
        }

        viewModelScope.launch {
            selectionsRepository.removeSelection(selection.condition)
        }
    }
}