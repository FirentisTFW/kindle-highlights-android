package com.firentistfw.kindlehighlights.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemHighlightListCellBinding
import com.firentistfw.kindlehighlights.models.Highlight

class HighlightListAdapter(private val highlights: List<Highlight>) :
    RecyclerView.Adapter<HighlightListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightListViewHolder {
        val binding =
            ItemHighlightListCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return HighlightListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightListViewHolder, position: Int) {
        val data: Highlight = highlights[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = highlights.size
}