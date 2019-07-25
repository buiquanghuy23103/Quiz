package com.example.quiz.quizedit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.BaseAnswerAdapter
import com.example.quiz.databinding.AnswerEditItemBinding
import com.example.quiz.model.Answer
import com.example.quiz.quizview.AnswerViewItem

class AnswerEditAdapter : BaseAnswerAdapter<AnswerEditItem>(){
    lateinit var itemClickListener: AnswerEditItem.OnListItemClickListener

    override fun getViewHolder(parent: ViewGroup): AnswerEditItem {
        return AnswerEditItem.from(parent).apply {
            clickListener = itemClickListener
        }
    }

}