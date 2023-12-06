package com.firentistfw.kindlehighlights.ui.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseFragment
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_highlight_list.rvHighlights
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightListFragment : BaseFragment() {
    override val layoutResId = R.layout.fragment_highlight_list

    private val viewModel: HighlightListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        fetchHighlights()
    }

    private fun initObservers() {
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Error -> {
                    // FIXME Show error state
                }

                is DataState.Loading -> {
                    // FIXME Sow loading state
                }

                is DataState.Success -> {
                    rvHighlights.layoutManager = LinearLayoutManager(context)

                    val highlights = state.data
                    val adapter = HighlightListAdapter(highlights)
                    rvHighlights.adapter = adapter

                    val dividerItemDecoration = DividerItemDecoration(rvHighlights.context)
                    rvHighlights.addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    private fun fetchHighlights() {
        viewModel.fetchHighlights()
    }

}