package com.example.quiz.quizview

import android.view.ViewGroup
import com.example.quiz.framework.BaseAnswerAdapter

class AnswerViewAdapter : BaseAnswerAdapter<AnswerViewItem>(){

    override fun getViewHolder(parent: ViewGroup): AnswerViewItem {
        return AnswerViewItem.from(parent)
    }
}