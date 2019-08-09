package com.example.quiz.quiz

import android.view.LayoutInflater
import com.example.quiz.framework.BaseListAdapter

class ChoiceListAdapter : BaseListAdapter<ChoiceListItem>() {
    lateinit var itemClickListener: ChoiceListItem.UIinterface

    override fun getViewHolder(inflater: LayoutInflater): ChoiceListItem {
        return ChoiceListItem.from(inflater).apply {
            clickListener = itemClickListener
        }
    }
}