package com.firentistfw.kindlehighlights.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.common.mocks.Mocks
import com.firentistfw.kindlehighlights.models.Highlight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HighlightListViewModel : BaseViewModel() {
    private val _dataState = MutableLiveData<DataState<List<Highlight>>>()
    val dataState: LiveData<DataState<List<Highlight>>> get() = _dataState

    fun fetchHighlights() {
        _dataState.value = DataState.Loading(true)

        viewModelScope.launch {
            try {
                // FIXME Fetch real data from repository
                delay(2000)

                val result = listOf(Mocks.exampleHighlight, Mocks.exampleHighlight2)
                _dataState.value = DataState.Success(result)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}