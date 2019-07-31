package com.example.quiz.quizlist

import android.view.LayoutInflater
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Quiz

class QuizItemHolder private constructor(private val binding: QuizItemBinding) :
    BaseListItem(binding) {

    companion object {
        fun from(inflater: LayoutInflater): QuizItemHolder {
            val binding = QuizItemBinding.inflate(inflater)
            return QuizItemHolder(binding)
        }
    }

    override fun bind(data: BaseData) {
        binding.quiz = data as Quiz
        binding.root.setOnClickListener {
            clickListener.onViewClick(adapterPosition)
        }
        binding.executePendingBindings()
    }
}