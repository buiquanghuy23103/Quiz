package com.example.quiz.framework

import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.model.Choice

class ListDiffCallback : DiffUtil.ItemCallback<BaseData>() {

    override fun areItemsTheSame(oldItem: BaseData, newItem: BaseData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BaseData, newItem: BaseData): Boolean {
        val checkIsTrue = if (oldItem is Choice && newItem is Choice)
            oldItem.isTrue == newItem.isTrue
        else true
        val checkText = oldItem.text == newItem.text
        val total = listOf(checkIsTrue, checkText)
        return total.all { eachResultIsTrue -> eachResultIsTrue }
    }
}