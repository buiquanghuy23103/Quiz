package com.example.quiz.quizview

import android.view.LayoutInflater
import android.view.View
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Answer

class AnswerViewItem private constructor(private val binding: AnswerViewItemBinding) :
    BaseListItem(binding)
{
    lateinit var clickListener: OnClickListener

    companion object{
        fun from(inflater: LayoutInflater): AnswerViewItem {
            val binding = AnswerViewItemBinding.inflate(inflater)
            return AnswerViewItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            answer = data as Answer
            binding.answerButton.setOnClickListener {
                clickListener.onClick(it, adapterPosition)
            }
            executePendingBindings()
        }
    }

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }


}