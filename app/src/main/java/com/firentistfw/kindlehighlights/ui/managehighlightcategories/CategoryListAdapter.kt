package com.firentistfw.kindlehighlights.ui.managehighlightcategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firentistfw.kindlehighlights.databinding.ItemCategoryListCellBinding
import com.firentistfw.kindlehighlights.storage.tables.DBCategory
import java.util.UUID

interface OnCategoryClickListener {
    fun onClick(categoryId: UUID)
}


class CategoryListAdapter(
    private val categories: List<DBCategory>,
    private val itemClickListener: OnCategoryClickListener,
) :
    RecyclerView.Adapter<CategoryListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val binding =
            ItemCategoryListCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return CategoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val data: DBCategory = categories[position]
        holder.bind(data, itemClickListener)
    }

    override fun getItemCount(): Int = categories.size
}