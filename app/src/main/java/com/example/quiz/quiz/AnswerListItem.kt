package com.example.quiz.quiz

import android.view.LayoutInflater
import android.view.View
import com.example.quiz.databinding.AnswerItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Answer

class AnswerListItem private constructor(private val binding: AnswerItemBinding) :
    BaseListItem(binding)
{
    lateinit var clickListener: OnClickListener

    companion object{
        fun from(inflater: LayoutInflater): AnswerListItem {
            val binding = AnswerItemBinding.inflate(inflater)
            return AnswerListItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            answer = data as Answer
            binding.answerButton.setOnClickListener {

                clickListener.onClick(it, data.id)
            }
            executePendingBindings()
        }
    }

    interface OnClickListener {
        fun onClick(view: View, answerId: Int)
    }


}