package com.firentistfw.kindlehighlights.ui.addcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.data.repository.CategoriesRepository
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class AddCategoryViewModel(
    private val categoriesRepository: CategoriesRepository,
) :
    BaseViewModel() {
    private val _requestState = MutableLiveData<RequestState<Nothing>>()
    val requestState: LiveData<RequestState<Nothing>> get() = _requestState

    fun addCategory(name: String) {
        // TODO Validate user input, make sure category name is unique

        _requestState.value = RequestState.Ongoing()

        val category = DBCategory(
            categoryId = UUID.randomUUID(),
            name = name,
            date = Date().time,
        )

        viewModelScope.launch {
            try {
                categoriesRepository.addCategory(category)
                _requestState.value = RequestState.Success()
            } catch (e: Exception) {
                _requestState.value = RequestState.Error(e.message)
            }
        }
    }
}