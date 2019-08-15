package com.example.quiz.categorylist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Category

class CategoryListAdapter : ListAdapter<Category, CategoryListItem>(CategoryListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListItem {
        return CategoryListItem.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryListItem, position: Int) {
        holder.bind(getItem(position))
    }
}