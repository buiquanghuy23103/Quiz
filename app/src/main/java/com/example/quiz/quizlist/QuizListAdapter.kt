package com.example.quiz.quizlist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Quiz

class QuizListAdapter
    : ListAdapter<Quiz, QuizItemHolder>(QuizListDiffCallBack()) {

    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuizItemHolder.from(parent)

    override fun onBindViewHolder(holder: QuizItemHolder, position: Int) {
        holder.bind(getItem(position))
        holder.clickListener = itemClickListener
    }

    interface OnItemClickListener{
        fun onItemViewClick(position: Int)
        fun onDeleteClick(quiz: Quiz)
    }
}