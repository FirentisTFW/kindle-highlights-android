package com.firentistfw.kindlehighlights.ui.selections

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivitySelectionsBinding
import com.firentistfw.kindlehighlights.storage.tables.authorAndTitleDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectionsActivity : BaseActivity() {
    private val viewModel: SelectionsViewModel by viewModel()

    private lateinit var binding: ActivitySelectionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchSelections()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.bookSelections.collect { selections ->
                val bookNames = selections.map {
                    it.book.authorAndTitleDisplay
                }

                binding.tvChosenBooks.text = bookNames.joinToString("\n")
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.categorySelections.collect { selections ->
                val categoryNames = selections.map {
                    it.category.name
                }

                binding.tvChosenCategories.text = categoryNames.joinToString("\n")
            }
        }
    }
}