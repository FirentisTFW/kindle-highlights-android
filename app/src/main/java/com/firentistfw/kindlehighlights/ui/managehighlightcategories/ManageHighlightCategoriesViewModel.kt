package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ManageHighlightCategoriesViewModel(
    private val categoriesRepository: CategoriesRepository,
) : BaseViewModel() {
    private val _assignedCategories = MutableStateFlow<List<DBCategory>>(emptyList())
    val assignedCategories: StateFlow<List<DBCategory>> get() = _assignedCategories.asStateFlow()

    private val _otherCategories = MutableStateFlow<List<DBCategory>>(emptyList())
    val otherCategories: StateFlow<List<DBCategory>> get() = _otherCategories.asStateFlow()

    fun fetchCategories(highlightId: UUID) {
        viewModelScope.launch {
            categoriesRepository.getAssignedCategoriesFlowForHighlight(highlightId).collect {
                _assignedCategories.value = it
            }
        }

        viewModelScope.launch {
            categoriesRepository.getPossibleCategoriesFlowForHighlight(highlightId).collect {
                _otherCategories.value = it
            }
        }
    }

    fun assignCategoryToHighlight(categoryId: UUID, highlightId: UUID) {
        viewModelScope.launch {
            categoriesRepository.assignCategoryToHighlight(categoryId, highlightId)
        }
    }

    fun removeCategoryFromHighlight(categoryId: UUID, highlightId: UUID) {
        viewModelScope.launch {
            categoriesRepository.removeCategoryFromHighlight(categoryId, highlightId)
        }
    }
}