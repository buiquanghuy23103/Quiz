package com.example.quiz.quizview

import androidx.lifecycle.LiveData
import com.example.quiz.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz

class QuizViewViewModel(id: Int) : BaseViewModel() {
    val quiz = repository.getQuizById(id)
    val answerList = repository.getAnswersByQuizId(id)
}