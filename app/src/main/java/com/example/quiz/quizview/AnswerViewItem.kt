package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.framework.BaseAnswerItem
import com.example.quiz.model.Answer

class AnswerViewItem(private val binding: AnswerViewItemBinding)
    : BaseAnswerItem(binding)
{

    companion object{
        fun from(parent: ViewGroup): AnswerViewItem{
            val inflater = LayoutInflater.from(parent.context)
            val binding = AnswerViewItemBinding.inflate(inflater)
            return AnswerViewItem(binding)
        }
    }

    override fun bind(answer: Answer){
        binding.answer = answer
        binding.answerButton.setOnClickListener {
            val color = if (answer.isTrue) Color.GREEN else Color.RED
            it.setBackgroundColor(color)
        }
    }

}