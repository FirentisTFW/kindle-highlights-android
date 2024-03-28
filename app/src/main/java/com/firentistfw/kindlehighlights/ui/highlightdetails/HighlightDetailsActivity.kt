package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.databinding.ActivityHighlightDetailsBinding
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.authorAndTitleDisplay
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.ManageHighlightCategoriesBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class HighlightDetailsActivity : BaseActivity() {
    private val viewModel: HighlightDetailsViewModel by viewModel()
    private val tag = "HighlightDetailsActivity"
    private lateinit var binding: ActivityHighlightDetailsBinding

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
        binding = ActivityHighlightDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.divider.background = ContextCompat.getDrawable(this, R.drawable.divider)

        initInteractions()
        initObservers()

        viewModel.fetchHighlight(highlightId)
        viewModel.fetchCategories(highlightId)
    }

    private fun initObservers() {
        observeHighlight()
        observeCategories()
    }

    private fun observeHighlight() {
        viewModel.dataState.observe(this) { state ->
            when (state) {
                is DataState.Error -> {
                    Log.d(tag, "An error occurred while fetching highlight")
                    // TODO Show error state
                }

                is DataState.Loading -> {
                    // TODO Show loading spinner
                }

                is DataState.Success -> {
                    val highlight = state.data

                    fillHighlightData(highlight)
                    initCategoriesButtonInteractions(highlight)
                }
            }
        }
    }

    private fun observeCategories() {
        lifecycleScope.launchWhenStarted {
            viewModel.assignedCategories.collect { categories ->
                val categoriesText =
                    if (categories.isEmpty()) getString(R.string.highlightDetails_noCategoriesAssigned)
                    else categories.joinToString("\n") {
                        "- ${it.name}"
                    }

                binding.tvAssignedCategories.text = categoriesText
            }
        }
    }

    private fun fillHighlightData(highlight: CompleteHighlight) {
        binding.tvQuote.text = highlight.highlight.content
        binding.tvNote.text = highlight.highlight.note
        binding.tvBook.text = highlight.book.authorAndTitleDisplay
        binding.tvDate.text = highlight.highlight.date
    }

    private fun initCategoriesButtonInteractions(highlight: CompleteHighlight) {
        binding.btnManageCategories.setOnClickListener {
            val categoriesBottomSheet =
                ManageHighlightCategoriesBottomSheetFragment(highlight.highlight.highlightId)
            categoriesBottomSheet.show(supportFragmentManager, categoriesBottomSheet.tag)
        }
    }
}