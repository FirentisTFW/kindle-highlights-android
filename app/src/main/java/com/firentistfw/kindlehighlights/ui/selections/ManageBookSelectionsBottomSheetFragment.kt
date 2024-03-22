package com.firentistfw.kindlehighlights.ui.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.databinding.FragmentManageBookSelectionsBottomSheetBinding
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class ManageBookSelectionsBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel: ManageBookSelectionsViewModel by viewModel()
    private lateinit var binding: FragmentManageBookSelectionsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(
        R.layout.fragment_manage_book_selections_bottom_sheet,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentManageBookSelectionsBottomSheetBinding.bind(view)

        initObservers()
        fetchBooksAndSelections()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.bookSelections.collect { selections ->
                val books = selections.map {
                    it.book
                }

                binding.rvSelectedBooks.layoutManager = LinearLayoutManager(context)

                val adapter = BookListAdapter(books, onSelectedBookClickListener)
                binding.rvSelectedBooks.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvSelectedBooks.context)

                binding.rvSelectedBooks.addItemDecoration(dividerItemDecoration)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.otherBooks.collect { books ->
                binding.rvOtherBooks.layoutManager = LinearLayoutManager(context)

                val adapter = BookListAdapter(books, onOtherBookClickListener)
                binding.rvOtherBooks.adapter = adapter

                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvOtherBooks.context)

                binding.rvOtherBooks.addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private val onSelectedBookClickListener = object : OnBookClickListener {
        override fun onClick(bookId: UUID) {
            viewModel.unselectBook(bookId)
        }
    }

    private val onOtherBookClickListener = object : OnBookClickListener {
        override fun onClick(bookId: UUID) {
            viewModel.selectBook(bookId)
        }
    }

    private fun fetchBooksAndSelections() {
        viewModel.fetchBooksAndSelections()
    }
}