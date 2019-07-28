package com.example.quiz.quizlist

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class QuizListAdapter
    : BaseListAdapter<QuizItemHolder>() {
    override fun getViewHolder(inflater: LayoutInflater) =
        QuizItemHolder.from(inflater)
}