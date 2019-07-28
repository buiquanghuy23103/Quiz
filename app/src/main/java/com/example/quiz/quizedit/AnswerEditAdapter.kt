package com.example.quiz.quizedit

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class AnswerEditAdapter : BaseListAdapter<AnswerEditItem>() {

    override fun getViewHolder(inflater: LayoutInflater) =
        AnswerEditItem.from(inflater)
}