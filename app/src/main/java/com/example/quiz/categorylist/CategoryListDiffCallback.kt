package com.example.quiz.categorylist

import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.model.Category

class CategoryListDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}