package com.example.quiz.categorylist

import androidx.lifecycle.ViewModel
import com.example.quiz.data.local.dao.CategoryDao
import javax.inject.Inject

class CategoryListViewModel @Inject constructor(
    categoryDao: CategoryDao
) : ViewModel() {

    val categoryList = categoryDao.getAll()
}