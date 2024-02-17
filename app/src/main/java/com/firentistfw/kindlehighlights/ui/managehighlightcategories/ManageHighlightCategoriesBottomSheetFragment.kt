package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.databinding.FragmentManageHighlightCategoriesBottomSheetBinding
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class ManageHighlightCategoriesBottomSheetFragment(
    private val highlightId: UUID,
) : BottomSheetDialogFragment() {
    private val viewModel: ManageHighlightCategoriesViewModel by viewModel()
    private lateinit var binding: FragmentManageHighlightCategoriesBottomSheetBinding

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
        binding = FragmentManageHighlightCategoriesBottomSheetBinding.bind(view)

        initObservers()
        fetchCategories()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            // TODO Handle loading and error states
            viewModel.assignedCategories.collect { categories ->
                binding.rvAssignedCategories.layoutManager = LinearLayoutManager(context)

                val adapter = CategoryListAdapter(categories)
                binding.rvAssignedCategories.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvAssignedCategories.context)

                binding.rvAssignedCategories.addItemDecoration(dividerItemDecoration)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.otherCategories.collect { categories ->
                binding.rvOtherCategories.layoutManager = LinearLayoutManager(context)

                val adapter = CategoryListAdapter(categories)
                binding.rvOtherCategories.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvOtherCategories.context)

                binding.rvOtherCategories.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private fun fetchCategories() {
        viewModel.fetchCategories(highlightId)
    }
}