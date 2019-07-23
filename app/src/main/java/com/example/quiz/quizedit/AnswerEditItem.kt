package com.example.quiz.quizedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.AnswerEditItemBinding
import com.example.quiz.model.Answer

class AnswerEditItem private constructor(private val binding: AnswerEditItemBinding)
    : RecyclerView.ViewHolder(binding.root){

    companion object{
        fun from(parent: ViewGroup): AnswerEditItem{
            val inflater = LayoutInflater.from(parent.context)
            val binding = AnswerEditItemBinding.inflate(inflater)
            return AnswerEditItem(binding)
        }
    }

    fun bind(answer: Answer){
        binding.answer = answer

    }
}