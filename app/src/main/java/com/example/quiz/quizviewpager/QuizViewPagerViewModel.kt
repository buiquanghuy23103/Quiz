package com.example.quiz.quizviewpager

import com.example.quiz.BaseViewModel

class QuizViewPagerViewModel: BaseViewModel() {
    val quizIdList = repository.getQuizIdList()
}
