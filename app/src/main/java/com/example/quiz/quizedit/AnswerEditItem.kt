package com.example.quiz.quizedit

import android.view.LayoutInflater
import com.example.quiz.databinding.AnswerEditItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Answer

class AnswerEditItem private constructor(private val binding: AnswerEditItemBinding) :
    BaseListItem(binding) {

    companion object{
        fun from(inflater: LayoutInflater): AnswerEditItem {
            val binding = AnswerEditItemBinding.inflate(inflater)
            return AnswerEditItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        binding.answer = data as Answer
        binding.deleteButton.setOnClickListener {
            clickListener.onDeleteButtonClick(data)
        }
    }
}