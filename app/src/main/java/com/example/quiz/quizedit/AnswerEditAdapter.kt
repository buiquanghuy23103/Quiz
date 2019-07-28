package com.example.quiz.quizedit

import android.view.ViewGroup
import com.example.quiz.framework.BaseAnswerAdapter

class AnswerEditAdapter : BaseAnswerAdapter<AnswerEditItem>(){

    override fun getViewHolder(parent: ViewGroup): AnswerEditItem {
        return AnswerEditItem.from(parent)
    }

}