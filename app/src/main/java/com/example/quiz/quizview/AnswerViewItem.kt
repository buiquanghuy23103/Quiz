package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Answer

class AnswerViewItem private constructor(private val binding: AnswerViewItemBinding) :
    BaseListItem(binding)
{

    companion object{
        fun from(inflater: LayoutInflater): AnswerViewItem {
            val binding = AnswerViewItemBinding.inflate(inflater)
            return AnswerViewItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        binding.answer = data as Answer
        binding.answerButton.setOnClickListener {
            val color = if (data.isTrue) Color.GREEN else Color.RED
            it.setBackgroundColor(color)
        }
    }
}