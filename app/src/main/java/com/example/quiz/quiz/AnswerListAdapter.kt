package com.example.quiz.quiz

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class AnswerListAdapter : BaseListAdapter<AnswerListItem>() {
    lateinit var itemClickListener: AnswerListItem.OnClickListener

    override fun getViewHolder(inflater: LayoutInflater): AnswerListItem {
        return AnswerListItem.from(inflater).apply {
            clickListener = itemClickListener
        }
    }
}