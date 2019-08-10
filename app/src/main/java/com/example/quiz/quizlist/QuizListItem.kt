package com.example.quiz.quizlist

import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Quiz
import timber.log.Timber

class QuizListItem private constructor(private val binding: QuizItemBinding) :
    BaseListItem(binding) {

    private var quizId = 0

    companion object {
        fun from(inflater: LayoutInflater): QuizListItem {
            val binding = QuizItemBinding.inflate(inflater)
            return QuizListItem(binding)
        }
    }

    override fun bind(data: BaseData) {
        with(binding) {
            quiz = data as Quiz
            quizId = data.id
            clickListener = createOnClickListener()
            executePendingBindings()
        }
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val navDirections = QuizListFragmentDirections
                .actionQuizListFragmentToQuizAskPagerFragment(quizId)
            Timber.i("quizId = $quizId")
            it.findNavController().navigate(navDirections)
        }
    }
}