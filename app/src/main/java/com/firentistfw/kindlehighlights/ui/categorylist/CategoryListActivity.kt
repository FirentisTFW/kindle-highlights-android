package com.firentistfw.kindlehighlights.ui.categorylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.databinding.ActivityCategoryListBinding
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.CategoryListAdapter
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.OnCategoryClickListener
import com.firentistfw.kindlehighlights.utils.ToastUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class CategoryListActivity : AppCompatActivity() {
    private val viewModel: CategoryListViewModel by viewModel()
    private val tag = "CategoryListActivity"
    private lateinit var binding: ActivityCategoryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        viewModel.fetchCategories()
    }

    private fun initObservers() {
        viewModel.dataState.observe(this) { state ->
            binding.pbCategories.visibility = View.GONE
            binding.rvCategories.visibility = View.GONE

            when (state) {
                is DataState.Error -> {
                    Log.d(tag, "An error occurred while fetching highlights")
                    // TODO Show error state
                }

                is DataState.Loading -> {
                    binding.pbCategories.visibility = View.VISIBLE
                }

                is DataState.Success -> {
                    binding.rvCategories.visibility = View.VISIBLE
                    binding.rvCategories.layoutManager = LinearLayoutManager(this)

                    val categories = state.data
                    val adapter = CategoryListAdapter(categories, onCategoryClickListener)
                    binding.rvCategories.adapter = adapter

                    val dividerItemDecoration = DividerItemDecoration(binding.rvCategories.context)
                    binding.rvCategories.addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    private val onCategoryClickListener = object : OnCategoryClickListener {
        override fun onClick(categoryId: UUID) {
            // TODO Go to category details page
            ToastUtils.showFeatureUnavailable(this@CategoryListActivity)
        }
    }
}