package com.example.quiz.quizedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.AnswerEditItemBinding
import com.example.quiz.model.Answer
import com.example.quiz.quizview.AnswerViewItem

class AnswerEditAdapter(private val answerList: List<Answer>) : RecyclerView.Adapter<AnswerEditAdapter.AnswerEditItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerEditItem {
        return AnswerEditItem.from(parent)
    }

    override fun getItemCount(): Int = answerList.size

    override fun onBindViewHolder(holder: AnswerEditItem, position: Int) {
        holder.bind(answerList[position])
    }

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
}