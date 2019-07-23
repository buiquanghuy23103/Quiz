package com.example.quiz.quizlist

import com.example.quiz.BaseViewModel
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    val quizBank = repository.getAllQuizzes()

    fun save(quiz: Quiz){
        ioScope.launch { repository.saveQuiz(quiz) }
    }
}