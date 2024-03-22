package com.firentistfw.kindlehighlights.ui.selections

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemBookListCellBinding
import com.firentistfw.kindlehighlights.storage.tables.DBBook

class BookListViewHolder (binding: ItemBookListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val titleTextView: TextView = binding.tvTitle

    fun bind(book: DBBook, itemClickListener: OnBookClickListener) {
        titleTextView.text = book.title

        itemView.setOnClickListener {
            itemClickListener.onClick(book.bookId)
        }
    }
}