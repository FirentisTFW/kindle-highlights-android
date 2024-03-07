package com.firentistfw.kindlehighlights.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import kotlinx.coroutines.launch

class HighlightListViewModel(
    private val repository: HighlightsRepository,
) : BaseViewModel() {
    private val _dataState = MutableLiveData<DataState<List<CompleteHighlight>>>()
    val dataState: LiveData<DataState<List<CompleteHighlight>>> get() = _dataState

    fun fetchHighlights() {
        _dataState.value = DataState.Loading()

        viewModelScope.launch {
            try {
                val result = repository.getDailyHighlights()
                _dataState.value = DataState.Success(result)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}