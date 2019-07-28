package com.example.quiz.framework

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListItem(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var clickListener: ClickListener

    abstract fun bind(data: BaseData)

    interface ClickListener {
        fun onViewClick(position: Int)
        fun onDeleteButtonClick(data: BaseData)
    }
}