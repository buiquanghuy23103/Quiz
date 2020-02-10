package com.example.quiz.categorylist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Category

class CategoryListAdapter(
    private val onClickListener: CategoryListItem.OnClickListener
) : ListAdapter<Category, CategoryListItem>(CategoryListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListItem {
        return CategoryListItem.from(parent, onClickListener)
    }

    override fun onBindViewHolder(holder: CategoryListItem, position: Int) {
        holder.bind(getItem(position))
    }
}