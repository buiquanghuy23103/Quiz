package com.example.quiz.quizedit

import com.example.quiz.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizEditViewModel(val quizId: Int) : BaseViewModel() {
    val quiz = repository.getQuizById(quizId)
    val answerList = repository.getAnswersByQuizId(quizId)
    var quizSync = Quiz("No change")
    var answerListSync = listOf(
        Answer(1234, "sample answer", false)
    )

    fun saveQuiz(){
        ioScope.launch { repository.saveQuiz(quizSync) }
    }

    fun saveAnswerList(){
        ioScope.launch { repository.saveAnswerList(answerListSync) }
    }
}
