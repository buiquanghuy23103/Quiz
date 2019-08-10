package com.example.quiz.categorylist

import androidx.lifecycle.Transformations
import com.example.quiz.framework.BaseViewModel

class CategoryListViewModel : BaseViewModel() {
    val categoryList = Transformations.map(quizDao.getAll()) {
        it.groupBy { quiz -> quiz.category }
            .keys
            .toList()
    }
}