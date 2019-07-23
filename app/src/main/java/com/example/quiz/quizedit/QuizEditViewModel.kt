package com.example.quiz.quizedit

import com.example.quiz.BaseViewModel
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizEditViewModel(id: Int) : BaseViewModel() {
    val quiz = repository.getQuizById(id)
    val answerList = repository.getAnswersByQuizId(id)
    var quizSync = Quiz("No change")

    fun saveQuiz(){
        ioScope.launch { repository.saveQuiz(quizSync) }
    }
}
