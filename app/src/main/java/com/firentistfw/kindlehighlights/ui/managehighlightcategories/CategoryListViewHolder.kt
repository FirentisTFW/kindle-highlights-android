package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemCategoryListCellBinding
import com.firentistfw.kindlehighlights.storage.tables.DBCategory

class CategoryListViewHolder (binding: ItemCategoryListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val nameTextView: TextView = binding.tvName

    fun bind(category: DBCategory, itemClickListener: OnCategoryClickListener) {
        nameTextView.text = category.name

        itemView.setOnClickListener {
            itemClickListener.onClick(category.categoryId)
        }
    }
}