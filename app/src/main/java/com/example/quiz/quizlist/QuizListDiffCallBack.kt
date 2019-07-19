package com.example.quiz.quizlist

import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.model.Quiz

class QuizListDiffCallBack: DiffUtil.ItemCallback<Quiz>() {
    override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        return oldItem == newItem
    }
}