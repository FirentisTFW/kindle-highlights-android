package com.firentistfw.kindlehighlights.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.models.Highlight
import kotlinx.android.synthetic.main.item_highlight_list_cell.view.*

class HighlightListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.title
    private val authorTextView: TextView = itemView.author
    private val contentTextView: TextView = itemView.content

    fun bind(highlight: Highlight) {
        titleTextView.text = highlight.book.title
        authorTextView.text = highlight.book.author
        contentTextView.text = highlight.content
    }
}