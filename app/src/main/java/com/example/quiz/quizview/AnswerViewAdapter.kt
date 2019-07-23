package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.model.Answer

class AnswerViewAdapter(private val answerList: List<Answer>) : RecyclerView.Adapter<AnswerViewItem>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewItem {
        return AnswerViewItem.from(parent)
    }

    override fun getItemCount(): Int = answerList.size

    override fun onBindViewHolder(holder: AnswerViewItem, position: Int) {
        holder.bind(answerList[position])
    }
}