package com.example.quiz.quizviewpager

import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel(categoryId: Int) : BaseViewModel() {
    val quizIdList = quizDao.getQuizIdListByCategory(categoryId)
}
