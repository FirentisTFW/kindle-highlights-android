package com.firentistfw.kindlehighlights.ui.common.highlightlist

import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemHighlightListCellBinding
import com.firentistfw.kindlehighlights.storage.model.CompleteHighlight
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsActivity

class HighlightListViewHolder(binding: ItemHighlightListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val titleTextView: TextView = binding.title
    private val authorTextView: TextView = binding.author
    private val contentTextView: TextView = binding.content

    fun bind(highlight: CompleteHighlight) {
        titleTextView.text = highlight.book.title
        authorTextView.text = highlight.book.author
        contentTextView.text = highlight.highlight.content

        itemView.setOnClickListener {
            goToHighlightDetails(highlight)
        }
    }

    private fun goToHighlightDetails(highlight: CompleteHighlight) {
        val intent = Intent(itemView.context, HighlightDetailsActivity::class.java)
        intent.putExtra(
            HighlightDetailsActivity.highlightIdKey,
            highlight.highlight.highlightId.toString(),
        )
        itemView.context.startActivity(intent)
    }
}