package com.example.quiz.quizedit

import com.example.quiz.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizEditViewModel(val quizId: Int) : BaseViewModel() {
    val quiz = repository.getQuizById(quizId)
    val answerList = repository.getAnswersByQuizId(quizId)
    var finalQuizForSaving = Quiz("No change")
    var finalAnswerListForSaving = listOf<Answer>()

    fun saveQuiz(){
        ioScope.launch { repository.saveQuiz(finalQuizForSaving) }
    }

    fun saveAnswerList(){
        ioScope.launch { repository.saveAnswerList(finalAnswerListForSaving) }
    }
}
