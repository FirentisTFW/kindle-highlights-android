package com.firentistfw.kindlehighlights.ui.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.launch

class CategoryListViewModel(
    private val categoriesRepository: CategoriesRepository,
) : BaseViewModel() {
    private val _dataState = MutableLiveData<DataState<List<DBCategory>>>()
    val dataState: LiveData<DataState<List<DBCategory>>> get() = _dataState

    fun fetchCategories() {
        _dataState.value = DataState.Loading()

        viewModelScope.launch {
            try {
                val result = categoriesRepository.getCategories()

                _dataState.value = DataState.Success(result)
            } catch (e: Exception) {
                _dataState.value = DataState.Error("Error fetching data: ${e.message}")
            }
        }
    }
}