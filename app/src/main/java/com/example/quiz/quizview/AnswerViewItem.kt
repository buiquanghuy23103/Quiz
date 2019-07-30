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
    var isClicked = false

    companion object{
        fun from(inflater: LayoutInflater): AnswerViewItem {
            val binding = AnswerViewItemBinding.inflate(inflater)
            return AnswerViewItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            answer = data as Answer
            answerButton.setOnClickListener {
                isClicked = !isClicked
                if (isClicked) {
                    it.setBackgroundColor(Color.BLUE)
                } else {
                    it.setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
    }
}