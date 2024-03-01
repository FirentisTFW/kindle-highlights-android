package com.firentistfw.kindlehighlights.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.firentistfw.kindlehighlights.common.BaseViewModel
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.data.repository.LocalFilesRepository
import com.firentistfw.kindlehighlights.utils.KindleClippingsParser
import kotlinx.coroutines.launch

class MainViewModel(
    private val localFilesRepository: LocalFilesRepository,
    private val clippingsParser: KindleClippingsParser,
) : BaseViewModel() {
    private val _fileImportRequestState = MutableLiveData<RequestState<Nothing>>()
    // FIXME Listen for result in activity
    val fileImportRequestState: LiveData<RequestState<Nothing>> get() = _fileImportRequestState

    fun importHighlightsFromFile(uri: Uri) {
        _fileImportRequestState.value = RequestState.Ongoing()

        viewModelScope.launch {
            try {
                val content = localFilesRepository.readFileContent(uri)

                val highlights = clippingsParser.createHighlightsFromRawClippingsInput(content)

                // FIXME Add highlights to database

                _fileImportRequestState.value = RequestState.Success()
            } catch (e: Exception) {
                _fileImportRequestState.value = RequestState.Error(e.message)
            }
        }
    }
}