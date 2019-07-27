package com.example.quiz.quizedit

import android.view.ViewGroup
import com.example.quiz.framework.BaseAnswerAdapter

class AnswerEditAdapter : BaseAnswerAdapter<AnswerEditItem>(){
    lateinit var itemClickListener: AnswerEditItem.OnListItemClickListener

    override fun getViewHolder(parent: ViewGroup): AnswerEditItem {
        return AnswerEditItem.from(parent).apply {
            clickListener = itemClickListener
        }
    }

}