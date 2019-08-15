package com.example.quiz.categorylist

import com.example.quiz.framework.BaseViewModel

class CategoryListViewModel : BaseViewModel() {
    val categoryList = categoryDao.getAll()
}