package com.firentistfw.kindlehighlights.ui.categorylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import com.firentistfw.kindlehighlights.utils.ToastUtils
import androidx.compose.runtime.livedata.observeAsState
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class CategoryListActivity : ComponentActivity() {
    private val viewModel: CategoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchCategories()

        setContent {
            val dataState by viewModel.dataState.observeAsState()
            CategoryListScreen(
                dataState = dataState,
                onCategoryClick = ::onCategoryClick,
            )
        }
    }

    private fun onCategoryClick(categoryId: UUID) {
        // TODO Go to category details page
        ToastUtils.showFeatureUnavailable(this@CategoryListActivity)
    }
}