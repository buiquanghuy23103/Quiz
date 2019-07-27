package com.example.quiz.framework

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.model.Answer

abstract class BaseAnswerItem(binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root)
{
    abstract fun bind(answer: Answer)
}