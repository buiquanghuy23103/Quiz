package com.example.quiz.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz

class QuizListAdapter : RecyclerView.Adapter<QuizListAdapter.QuizItemHolder>(){
    private val TAG = "QuizListAdapter"
    var quizBank = listOf<Quiz>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizItemHolder {
        return QuizItemHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return quizBank.size
    }

    override fun onBindViewHolder(holder: QuizItemHolder, position: Int) {
        holder.bind(quizBank[position])
    }

    class QuizItemHolder private constructor(binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val TAG = "QuizItemHolder"
        private val viewBinding = binding

        companion object{
            fun from(parent: ViewGroup): QuizItemHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil
                    .inflate<QuizItemBinding>(inflater, R.layout.quiz_item, parent, false)
                return QuizItemHolder(binding)
            }
        }

        fun bind(quiz: Quiz){
            Log.i(TAG, "current quiz is: " + quiz.question)
            viewBinding.quiz = quiz
            viewBinding.root.setOnClickListener{view: View ->
                val action = QuizListFragmentDirections.actionQuizListFragmentToQuizAskFragment(quiz.id)
                Log.i(TAG, "quizId = " + quiz.id)
                view.findNavController().navigate(action)
            }
            viewBinding.executePendingBindings()
        }
    }
}