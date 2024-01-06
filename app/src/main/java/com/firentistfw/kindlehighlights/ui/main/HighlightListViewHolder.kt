package com.firentistfw.kindlehighlights.ui.main

import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.databinding.ItemHighlightListCellBinding
import com.firentistfw.kindlehighlights.models.Highlight
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsActivity
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsArguments

class HighlightListViewHolder(binding: ItemHighlightListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val titleTextView: TextView = binding.title
    private val authorTextView: TextView = binding.author
    private val contentTextView: TextView = binding.content

    fun bind(highlight: Highlight) {
        titleTextView.text = highlight.book.title
        authorTextView.text = highlight.book.author
        contentTextView.text = highlight.content

        itemView.setOnClickListener {
            goToHighlightDetails(highlight)
        }
    }

    private fun goToHighlightDetails(highlight: Highlight) {
        val intent = Intent(itemView.context, HighlightDetailsActivity::class.java)
        intent.putExtra(Constants.argumentsKey, HighlightDetailsArguments(highlight))
        itemView.context.startActivity(intent)
    }
}