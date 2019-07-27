package com.example.quiz.quizview

import com.example.quiz.framework.BaseViewModel

class QuizViewViewModel(id: Int) : BaseViewModel() {
    val quiz = repository.getQuizById(id)
    val answerList = repository.getAnswersByQuizId(id)
}