package com.example.quiz.framework

import androidx.recyclerview.widget.DiffUtil

class ListDiffCallback : DiffUtil.ItemCallback<BaseData>() {

    override fun areItemsTheSame(oldItem: BaseData, newItem: BaseData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BaseData, newItem: BaseData): Boolean {
        return oldItem.text == newItem.text
    }
}