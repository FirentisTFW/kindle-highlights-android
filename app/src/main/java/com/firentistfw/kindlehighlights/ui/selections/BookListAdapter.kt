package com.firentistfw.kindlehighlights.ui.selections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemBookListCellBinding
import com.firentistfw.kindlehighlights.storage.tables.DBBook
import java.util.UUID

interface OnBookClickListener {
    fun onClick(bookId: UUID)
}

class BookListAdapter(
    private val books: List<DBBook>,
    private val itemClickListener: OnBookClickListener,
) :
    RecyclerView.Adapter<BookListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding =
            ItemBookListCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val data: DBBook = books[position]
        holder.bind(data, itemClickListener)
    }

    override fun getItemCount(): Int = books.size
}