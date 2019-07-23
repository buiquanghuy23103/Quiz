package com.example.quiz.quizview

import androidx.lifecycle.LiveData
import com.example.quiz.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz

class QuizViewViewModel(private var id: Int) : BaseViewModel() {
    val quiz: LiveData<Quiz>
    val answerList: LiveData<List<Answer>>

    init {
        quiz = repository.getQuizById(id)
        answerList = repository.getAnswersByQuizId(id)
    }
}