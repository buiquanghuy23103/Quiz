package com.example.quiz.quizlist

import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Quiz

class QuizItemHolder private constructor(private val binding: QuizItemBinding) :
    BaseListItem(binding) {

    companion object {
        fun from(inflater: LayoutInflater): QuizItemHolder {
            val binding = QuizItemBinding.inflate(inflater)
            return QuizItemHolder(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            quiz = data as Quiz
            clickListener = createOnClickListener()
            executePendingBindings()
        }
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val navDirections = QuizListFragmentDirections
                .actionQuizListFragmentToQuizAskPagerFragment(adapterPosition)
            it.findNavController().navigate(navDirections)
        }
    }
}