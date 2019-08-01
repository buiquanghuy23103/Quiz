package com.example.quiz.quizlist

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class QuizListAdapter
    : BaseListAdapter<QuizListItem>() {
    override fun getViewHolder(inflater: LayoutInflater) =
        QuizListItem.from(inflater)
}