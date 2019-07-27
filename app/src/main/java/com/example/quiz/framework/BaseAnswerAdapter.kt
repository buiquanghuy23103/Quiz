package com.example.quiz.framework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.model.Answer

abstract class BaseAnswerAdapter<T: BaseAnswerItem>
    : RecyclerView.Adapter<T>()
{
    var answerList = listOf<Answer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getViewHolder(parent: ViewGroup) : T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return getViewHolder(parent)
    }

    override fun getItemCount(): Int = answerList.size

    override fun onBindViewHolder(holder: T, position: Int) {
        val answer = answerList[position]
        holder.bind(answer)
    }
}