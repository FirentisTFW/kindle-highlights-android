package com.firentistfw.kindlehighlights.ui.common.highlightlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.ui.common.highlightlist.HighlightListType.*
import kotlinx.coroutines.launch
import java.util.Date

class HighlightListViewModel(
    private val repository: HighlightsRepository,
) : BaseViewModel() {
    private val _dataState = MutableLiveData<DataState<List<CompleteHighlight>>>()
    val dataState: LiveData<DataState<List<CompleteHighlight>>> get() = _dataState

    private val highlightsCount = 5

    fun fetchHighlights(listType: HighlightListType) {
        _dataState.value = DataState.Loading()

        viewModelScope.launch {
            try {
                val result = when (listType) {
                    All -> repository.getAllHighlights()
                    Daily -> repository.getDailyHighlights(highlightsCount, Date())
                }
                _dataState.value = DataState.Success(result)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}