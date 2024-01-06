package com.firentistfw.kindlehighlights.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.data.repository.HighlightsRepository
import com.firentistfw.kindlehighlights.models.Highlight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HighlightListViewModel(
    private val repository: HighlightsRepository,
) : BaseViewModel() {
    private val _dataState = MutableLiveData<DataState<List<Highlight>>>()
    val dataState: LiveData<DataState<List<Highlight>>> get() = _dataState

    fun fetchHighlights() {
        _dataState.value = DataState.Loading(true)

        viewModelScope.launch {
            try {
                val result = repository.dailyHighlights()
                _dataState.value = DataState.Success(result)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}