package com.example.quiz.quizviewpager

import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel(category: String) : BaseViewModel() {
    val quizIdList = quizDao.getQuizIdListByCategory(category)
}
