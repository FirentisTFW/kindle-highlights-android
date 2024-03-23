package com.firentistfw.kindlehighlights.ui.selections

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.SelectionsRepository
import com.firentistfw.kindlehighlights.storage.model.BookSelection
import com.firentistfw.kindlehighlights.storage.model.CategorySelection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectionsViewModel(
    private val selectionsRepository: SelectionsRepository,
) : BaseViewModel() {
    private val _bookSelections = MutableStateFlow<List<BookSelection>>(emptyList())
    val bookSelections: StateFlow<List<BookSelection>> get() = _bookSelections.asStateFlow()

    private val _categorySelections = MutableStateFlow<List<CategorySelection>>(emptyList())
    val categorySelections: StateFlow<List<CategorySelection>> get() = _categorySelections.asStateFlow()

    fun fetchSelections() {
        viewModelScope.launch {
            selectionsRepository.getBookSelectionsFlow().collect {
                _bookSelections.value = it
            }
        }
        viewModelScope.launch {
            selectionsRepository.getCategorySelectionsFlow().collect {
                _categorySelections.value = it
            }
        }
    }
}