package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.storage.tables.DBHighlight
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.ManageHighlightCategoriesBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class HighlightDetailsActivity : BaseActivity() {
    private val viewModel: HighlightDetailsViewModel by viewModel()

    companion object {
        const val highlightIdKey = "highlight_id"
    }

    private val highlightId: UUID
        get() {
            val id = intent.getStringExtra(highlightIdKey)
            return UUID.fromString(id)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchHighlight(highlightId)
        viewModel.fetchCategories(highlightId)

        setContent {
            val state by viewModel.dataState.observeAsState()
            val categories by viewModel.assignedCategories.collectAsState()
            HighlightDetailsScreen(
                state = state,
                categories = categories,
                onManageCategoriesClick = ::onManageCategoriesClick,
                onRemoveHighlightClick = ::onRemoveHighlightClick,
            )
        }
    }

    private fun onManageCategoriesClick(highlightId: UUID) {
        val categoriesBottomSheet =
            ManageHighlightCategoriesBottomSheetFragment(highlightId)
        categoriesBottomSheet.show(supportFragmentManager, categoriesBottomSheet.tag)
    }

    private fun onRemoveHighlightClick(highlight: DBHighlight) {
        showRemoveHighlightConfirmationDialog(highlight)
    }

    private fun showRemoveHighlightConfirmationDialog(highlight: DBHighlight) {
        AlertDialog.Builder(this)
            .setTitle(R.string.highlightDetails_removeHighlightConfirmationDialog_title)
            .setMessage(R.string.highlightDetails_removeHighlightConfirmationDialog_message)
            .setPositiveButton(R.string.common_yes) { _, _ -> removeHighlight(highlight) }
            .setNegativeButton(R.string.common_no, null)
            .show()
    }

    private fun removeHighlight(highlight: DBHighlight) {
        viewModel.deleteHighlight(highlight)
        finish()
        // TODO Update highlight list (as one of its items could've just been deleted)
    }
}