package com.example.quiz.quizlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz

class QuizListAdapter : ListAdapter<Quiz, QuizListAdapter.QuizItemHolder>(QuizListDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuizItemHolder.from(parent)

    override fun onBindViewHolder(holder: QuizItemHolder, position: Int) {
        holder.apply {
            quiz = getItem(position)
            index = position
        }
        holder.bind()
    }

    class QuizItemHolder private constructor(private val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root){
        var quiz = Quiz("Error in QuizItemHolder")
        var index = 0

        companion object{
            fun from(parent: ViewGroup): QuizItemHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = QuizItemBinding.inflate(inflater, parent, false)
                return QuizItemHolder(binding)
            }
        }

        fun bind(){
            binding.quiz = quiz
            binding.root.setOnClickListener{view: View ->
                val action =
                    QuizListFragmentDirections.actionQuizListFragmentToQuizAskPagerFragment(
                        index
                    )
                view.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }
}