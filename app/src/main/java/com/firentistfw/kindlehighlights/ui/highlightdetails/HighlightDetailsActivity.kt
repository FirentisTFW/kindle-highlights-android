package com.firentistfw.kindlehighlights.ui.highlightdetails

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.databinding.ActivityHighlightDetailsBinding
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.storage.tables.authorAndTitleDisplay
import com.firentistfw.kindlehighlights.ui.managehighlightcategories.ManageHighlightCategoriesBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightDetailsActivity : BaseActivity() {
    private val viewModel: HighlightDetailsViewModel by viewModel()
    private lateinit var binding: ActivityHighlightDetailsBinding

    private val highlight: CompleteHighlight
        get() {
            // TODO Accept only highlightId in arguments and fetch the highlight from repository
            val arguments =
                intent.getParcelableExtra<HighlightDetailsArguments>(Constants.argumentsKey)
            return arguments!!.highlight
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.divider.background = ContextCompat.getDrawable(this, R.drawable.divider)
        fillInitialValues()

        initInteractions()
        initObservers()

        viewModel.fetchHighlightCategories(highlight.highlight.highlightId)
    }

    private fun fillInitialValues() {
        val highlight = highlight

        binding.tvQuote.text = highlight.highlight.content
        binding.tvNote.text = highlight.highlight.note
        binding.tvBook.text = highlight.book.authorAndTitleDisplay
        binding.tvDate.text = highlight.highlight.date
    }

    override fun initInteractions() {
        super.initInteractions()

        binding.btnManageCategories.setOnClickListener {
            val categoriesBottomSheet = ManageHighlightCategoriesBottomSheetFragment(highlight.highlight.highlightId)
            categoriesBottomSheet.show(supportFragmentManager, categoriesBottomSheet.tag)
        }
    }

    private fun initObservers() {
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
}