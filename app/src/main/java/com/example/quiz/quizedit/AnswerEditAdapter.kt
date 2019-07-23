package com.example.quiz.quizedit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.model.Answer
import com.example.quiz.quizview.AnswerViewItem

class AnswerEditAdapter(private val answerList: List<Answer>) : RecyclerView.Adapter<AnswerEditItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerEditItem {
        return AnswerEditItem.from(parent)
    }

    override fun getItemCount(): Int = answerList.size

    override fun onBindViewHolder(holder: AnswerEditItem, position: Int) {
        holder.bind(answerList[position])
    }
}