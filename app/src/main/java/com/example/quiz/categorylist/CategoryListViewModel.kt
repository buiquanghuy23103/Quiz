package com.example.quiz.categorylist

import androidx.lifecycle.Transformations
import com.example.quiz.framework.BaseViewModel

class CategoryListViewModel : BaseViewModel() {
    val categoryList = Transformations.map(categoryDao.getAll()) { categoryList ->
        categoryList.map { category -> category.text }
    }
}