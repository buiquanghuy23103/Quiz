package com.example.quiz.quizlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz

class QuizItemHolder private constructor(private val binding: QuizItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    lateinit var clickListener: QuizListAdapter.OnItemClickListener

    companion object {
        fun from(parent: ViewGroup): QuizItemHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = QuizItemBinding.inflate(inflater, parent, false)
            return QuizItemHolder(binding)
        }
    }

    fun bind(quiz: Quiz) {
        binding.quiz = quiz
        binding.root.setOnClickListener {
            // ViewHolder should not be responsible for navigating to another fragment
            // It should be a fragment's job => delegate the behavior of item-click to
            // QuizListFragment using OnItemClickListener interface
            clickListener.onItemViewClick(adapterPosition)
        }

        binding.quizItemDeleteButton.setOnClickListener {
            clickListener.onDeleteClick(quiz)
        }
        binding.executePendingBindings()
    }
}