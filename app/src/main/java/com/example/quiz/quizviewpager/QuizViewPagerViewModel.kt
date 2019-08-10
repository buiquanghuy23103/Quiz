package com.example.quiz.quizviewpager

import androidx.lifecycle.Transformations
import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel: BaseViewModel() {
    val quizIdList = Transformations.map(quizDao.getAll()) {
        it.map { quiz -> quiz.id }
    }
}
