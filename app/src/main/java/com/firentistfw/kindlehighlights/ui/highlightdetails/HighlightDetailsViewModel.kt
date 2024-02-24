package com.firentistfw.kindlehighlights.ui.highlightdetails

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HighlightDetailsViewModel(
    private val categoriesRepository: CategoriesRepository,
) : BaseViewModel() {
    private val _assignedCategories = MutableStateFlow<List<DBCategory>>(emptyList())
    val assignedCategories: StateFlow<List<DBCategory>> get() = _assignedCategories.asStateFlow()

    fun fetchHighlightCategories(highlightId: UUID) {
        viewModelScope.launch {
            categoriesRepository.getAssignedCategoriesFlowForHighlight(highlightId).collect {
                _assignedCategories.value = it
            }
        }

    }
}