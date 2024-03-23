package com.firentistfw.kindlehighlights.ui.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.databinding.FragmentManageCategorySelectionsBottomSheetBinding
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.CategoryListAdapter
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.OnCategoryClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class ManageCategorySelectionsBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel: ManageCategorySelectionsViewModel by viewModel()
    private lateinit var binding: FragmentManageCategorySelectionsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(
        R.layout.fragment_manage_category_selections_bottom_sheet,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentManageCategorySelectionsBottomSheetBinding.bind(view)

        initObservers()
        fetchCategoriesAndSelections()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.categorySelections.collect { selections ->
                val categories = selections.map {
                    it.category
                }

                binding.rvSelectedCategories.layoutManager = LinearLayoutManager(context)

                val adapter = CategoryListAdapter(categories, onSelectedCategoryClickListener)
                binding.rvSelectedCategories.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvSelectedCategories.context)

                binding.rvSelectedCategories.addItemDecoration(dividerItemDecoration)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.otherCategories.collect { categories ->
                binding.rvOtherCategories.layoutManager = LinearLayoutManager(context)

                val adapter = CategoryListAdapter(categories, onOtherCategoryClickListener)
                binding.rvOtherCategories.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvOtherCategories.context)

                binding.rvOtherCategories.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private val onSelectedCategoryClickListener = object : OnCategoryClickListener {
        override fun onClick(categoryId: UUID) {
            viewModel.unselectCategory(categoryId)
        }
    }

    private val onOtherCategoryClickListener = object : OnCategoryClickListener {
        override fun onClick(categoryId: UUID) {
            viewModel.selectCategory(categoryId)
        }
    }

    private fun fetchCategoriesAndSelections() {
        viewModel.fetchCategoriesAndSelections()
    }
}