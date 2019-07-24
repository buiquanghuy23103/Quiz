package com.example.quiz.quizlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz

class QuizListAdapter
    : ListAdapter<Quiz, QuizListAdapter.QuizItemHolder>(QuizListDiffCallBack()){

    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuizItemHolder.from(parent)

    override fun onBindViewHolder(holder: QuizItemHolder, position: Int) {
        holder.apply {
            quiz = getItem(position)
            clickListener = itemClickListener
        }
        holder.bind()
    }

    interface OnItemClickListener{
        fun onItemViewClick(position: Int)
        fun onDeleteClick(quiz: Quiz)
    }

    class QuizItemHolder private constructor(private val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root){
        var quiz = Quiz("Error in QuizItemHolder")
        lateinit var clickListener: OnItemClickListener

        companion object{
            fun from(parent: ViewGroup): QuizItemHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = QuizItemBinding.inflate(inflater, parent, false)
                return QuizItemHolder(binding)
            }
        }

        fun bind(){
            binding.quiz = quiz
            binding.root.setOnClickListener{
                // ViewHolder should not be responsible for navigating to another fragment
                // It should be a fragment's job => delegate the behavior of item-click to
                // QuizListFragment using OnItemClickListener interface
                clickListener.onItemViewClick(adapterPosition)
            }

            binding.quizItemDeleteButton.setOnClickListener {
                clickListener.onDeleteClick(quiz)
            }
            binding.executePendingBindings()
        }
    }
}