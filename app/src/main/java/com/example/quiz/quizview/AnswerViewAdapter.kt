package com.example.quiz.quizview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.BaseAnswerAdapter
import com.example.quiz.databinding.AnswerViewItemBinding
import com.example.quiz.model.Answer

class AnswerViewAdapter : BaseAnswerAdapter<AnswerViewItem>(){

    override fun getViewHolder(parent: ViewGroup): AnswerViewItem {
        return AnswerViewItem.from(parent)
    }
}