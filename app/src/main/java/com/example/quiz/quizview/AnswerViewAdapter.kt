package com.example.quiz.quizview

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class AnswerViewAdapter : BaseListAdapter<AnswerViewItem>() {
    lateinit var itemClickListener: AnswerViewItem.OnClickListener

    override fun getViewHolder(inflater: LayoutInflater): AnswerViewItem {
        return AnswerViewItem.from(inflater).apply {
            clickListener = itemClickListener
        }
    }
}