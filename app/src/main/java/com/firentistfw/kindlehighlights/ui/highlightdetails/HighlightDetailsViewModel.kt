package com.firentistfw.kindlehighlights.ui.highlightdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HighlightDetailsViewModel(
    private val highlightsRepository: HighlightsRepository,
    private val categoriesRepository: CategoriesRepository,
) : BaseViewModel() {
    private val _assignedCategories = MutableStateFlow<List<DBCategory>>(emptyList())
    val assignedCategories: StateFlow<List<DBCategory>> get() = _assignedCategories.asStateFlow()

    private val _dataState = MutableLiveData<DataState<CompleteHighlight>>()
    val dataState: LiveData<DataState<CompleteHighlight>> get() = _dataState

    fun fetchHighlight(highlightId: UUID) {
        viewModelScope.launch {
            _dataState.value = DataState.Loading()

            try {
                val highlight = highlightsRepository.getHighlightById(highlightId)
                _dataState.value = DataState.Success(highlight)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching highlight: ${e.message}")
            }
        }
    }

    fun fetchCategories(highlightId: UUID) {
        viewModelScope.launch {
            categoriesRepository.getAssignedCategoriesFlowForHighlight(highlightId).collect {
                _assignedCategories.value = it
            }
        }
    }
}