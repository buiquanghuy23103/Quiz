package com.example.quiz.quiz

import android.view.LayoutInflater
import android.view.View
import com.example.quiz.databinding.ChoiceListItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Choice

class ChoiceListItem private constructor(private val binding: ChoiceListItemBinding) :
    BaseListItem(binding)
{
    lateinit var clickListener: UIinterface

    companion object{
        fun from(inflater: LayoutInflater): ChoiceListItem {
            val binding = ChoiceListItemBinding.inflate(inflater)
            return ChoiceListItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            answer = data as Choice
            answerButton.let { view ->
                clickListener.setBackgroundColor(view, data.id)
                view.setOnClickListener { clickListener.onClick(data.id) }
            }
            executePendingBindings()
        }
    }

    interface UIinterface {
        fun onClick(answerId: Int)
        fun setBackgroundColor(view: View, answerId: Int)
    }


}