package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firentistfw.kindlehighlights.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class ManageHighlightCategoriesBottomSheetFragment(
    private val highlightId: UUID,
) : BottomSheetDialogFragment() {
    private val viewModel: ManageHighlightCategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(
        R.layout.fragment_manage_highlight_categories_bottom_sheet,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModel.fetchCategories(highlightId)
    }
}