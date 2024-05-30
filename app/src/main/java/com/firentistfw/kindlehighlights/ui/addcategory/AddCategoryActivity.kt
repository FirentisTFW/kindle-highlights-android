package com.firentistfw.kindlehighlights.ui.addcategory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.RequestState
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCategoryActivity : ComponentActivity() {
    private val viewModel: AddCategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInteractions()

        setContent {
            val requestState by viewModel.requestState.observeAsState()
            AddCategoryScreen(
                buttonEnabled = requestState !is RequestState.Ongoing,
                onButtonClick = ::onButtonClick,
            )
        }
    }

    private fun initInteractions() {
        viewModel.requestState.observe(this) { state ->
            when (state) {
                is RequestState.Error -> {
                    ToastUtils.showError(this, state.error)
                }

                is RequestState.Success -> {
                    ToastUtils.showSimpleToast(
                        this,
                        getString(R.string.addCategory_categoryAddedToast)
                    )
                }

                is RequestState.Ongoing -> {
                    /* No-op */
                }
            }
        }
    }

    private fun onButtonClick(categoryName: String) {
        viewModel.addCategory(name = categoryName)
    }
}