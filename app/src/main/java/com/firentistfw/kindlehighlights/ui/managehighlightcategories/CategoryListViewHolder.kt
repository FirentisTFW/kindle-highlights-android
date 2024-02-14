package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemCategoryListCellBinding
import com.firentistfw.kindlehighlights.storage.tables.DBCategory

class CategoryListViewHolder (binding: ItemCategoryListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val nameTextView: TextView = binding.tvName

    fun bind(category: DBCategory) {
        nameTextView.text = category.name

        itemView.setOnClickListener {
            // FIXME Assign category to highlight (or what if it's already assigned to the highlight?)
        }
    }
}