package com.firentistfw.kindlehighlights.ui.selections

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.firentistfw.kindlehighlights.common.BaseActivity
import com.firentistfw.kindlehighlights.databinding.ActivitySelectionsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectionsActivity : BaseActivity() {
    private val viewModel: SelectionsViewModel by viewModel()

    private lateinit var binding: ActivitySelectionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchSelections()
        initInteractions()
        initObservers()
    }


    override fun initInteractions() {
        super.initInteractions()

        binding.btnManageBooks.setOnClickListener {
            val booksBottomSheet = ManageBookSelectionsBottomSheetFragment()
            booksBottomSheet.show(supportFragmentManager, booksBottomSheet.tag)
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.bookSelections.collect { selections ->
                val bookTitles = selections.map {
                    it.book.title
                }

                binding.tvChosenBooks.text = bookTitles.joinToString("\n")
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