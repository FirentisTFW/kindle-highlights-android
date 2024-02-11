package com.firentistfw.kindlehighlights.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.firentistfw.kindlehighlights.R
import com.firentistfw.kindlehighlights.common.BaseFragment
import com.firentistfw.kindlehighlights.common.DataState
import com.firentistfw.kindlehighlights.databinding.FragmentHighlightListBinding
import com.firentistfw.kindlehighlights.ui.common.DividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class HighlightListFragment : BaseFragment() {
    override val layoutResId = R.layout.fragment_highlight_list

    private val viewModel: HighlightListViewModel by viewModel()
    private val tag = "HighlightListFragment"
    private lateinit var binding: FragmentHighlightListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHighlightListBinding.bind(view)

        initObservers()
        fetchHighlights()
    }

    private fun initObservers() {
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.circularProgressBar.visibility = View.GONE
            binding.rvHighlights.visibility = View.GONE

            when (state) {
                is DataState.Error -> {
                    Log.d(tag, "An error occurred while fetching highlights")
                    // TODO Show error state
                }

                is DataState.Loading -> {
                    binding.circularProgressBar.visibility = View.VISIBLE
                }

                is DataState.Success -> {
                    // TODO Handle empty list case - display a button to import highlights
                    //  or modify daily conditions

                    binding.rvHighlights.visibility = View.VISIBLE
                    binding.rvHighlights.layoutManager = LinearLayoutManager(context)

                    val highlights = state.data
                    val adapter = HighlightListAdapter(highlights)
                    binding.rvHighlights.adapter = adapter

                    val dividerItemDecoration = DividerItemDecoration(binding.rvHighlights.context)
                    binding.rvHighlights.addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    private fun fetchHighlights() {
        viewModel.fetchHighlights()
    }
}