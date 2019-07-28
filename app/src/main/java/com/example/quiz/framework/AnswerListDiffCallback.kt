package com.example.quiz.framework

import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.model.Answer

class AnswerListDiffCallback : DiffUtil.ItemCallback<Answer>() {

    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        val checkText = oldItem.text == newItem.text
        val checkIsTrue = oldItem.isTrue == newItem.isTrue
        val total = listOf(checkText, checkIsTrue)
        return total.all { checkResult -> checkResult }
    }
}