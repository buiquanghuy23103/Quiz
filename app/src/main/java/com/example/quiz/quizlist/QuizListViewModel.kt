package com.example.quiz.quizlist

import com.example.quiz.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    val quizBank = repository.getAllQuizzes()

    fun saveQuiz(quiz: Quiz){
        ioScope.launch { repository.saveQuiz(quiz) }
    }

    fun saveAnswerList(answerList: List<Answer>){
        ioScope.launch { repository.saveAnswerList(answerList) }
    }
}