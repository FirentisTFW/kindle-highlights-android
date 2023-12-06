package com.firentistfw.kindlehighlights.ui.main

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.common.Constants
import com.firentistfw.kindlehighlights.models.Highlight
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsActivity
import com.firentistfw.kindlehighlights.ui.highlightdetails.HighlightDetailsArguments
import kotlinx.android.synthetic.main.item_highlight_list_cell.view.*

class HighlightListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.title
    private val authorTextView: TextView = itemView.author
    private val contentTextView: TextView = itemView.content

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