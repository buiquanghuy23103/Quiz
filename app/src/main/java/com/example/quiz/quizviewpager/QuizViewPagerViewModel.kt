package com.example.quiz.quizviewpager

import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel: BaseViewModel() {
    val quizIdList = quizDao.getIdList()
}
