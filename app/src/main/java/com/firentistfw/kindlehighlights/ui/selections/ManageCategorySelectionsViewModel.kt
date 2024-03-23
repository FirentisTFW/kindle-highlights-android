package com.firentistfw.kindlehighlights.ui.selections

import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.data.repository.SelectionsRepository
import com.firentistfw.kindlehighlights.storage.model.CategorySelection
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import com.firentistfw.kindlehighlights.storage.tables.SelectionCondition
import com.firentistfw.kindlehighlights.storage.tables.SelectionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ManageCategorySelectionsViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val selectionsRepository: SelectionsRepository,
) : BaseViewModel() {
    private val _categorySelections = MutableStateFlow<List<CategorySelection>>(emptyList())
    val categorySelections: StateFlow<List<CategorySelection>> get() = _categorySelections.asStateFlow()

    private val _otherCategories = MutableStateFlow<List<DBCategory>>(emptyList())
    val otherCategories: StateFlow<List<DBCategory>> get() = _otherCategories.asStateFlow()

    fun fetchCategoriesAndSelections() {
        viewModelScope.launch {
            selectionsRepository.getCategorySelectionsFlow().collect {
                _categorySelections.value = it
            }
        }

        viewModelScope.launch {
            categoriesRepository.getNotSelectedCategoriesFlow().collect {
                _otherCategories.value = it
            }
        }
    }

    fun selectCategory(categoryId: UUID) {
        viewModelScope.launch {
            selectionsRepository.addSelection(
                SelectionCondition(
                    id = UUID.randomUUID(),
                    type = SelectionType.Category,
                    selectionId = categoryId,
                )
            )
        }
    }

    fun unselectCategory(categoryId: UUID) {
        val selection = categorySelections.value.first {
            it.category.categoryId == categoryId
        }

        viewModelScope.launch {
            selectionsRepository.removeSelection(selection.condition)
        }
    }
}