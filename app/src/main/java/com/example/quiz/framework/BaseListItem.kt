package com.example.quiz.framework

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListItem(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: BaseData)
}