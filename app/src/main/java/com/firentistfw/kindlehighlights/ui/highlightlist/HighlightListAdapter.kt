package com.firentistfw.kindlehighlights.ui.highlightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemHighlightListCellBinding
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight

class HighlightListAdapter(private val highlights: List<CompleteHighlight>) :
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
        val data: CompleteHighlight = highlights[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = highlights.size
}