package com.example.quiz.quizlist

import com.example.quiz.framework.BaseViewModel

class QuizListViewModel : BaseViewModel() {
    val quizList = quizDao.getAll()
}