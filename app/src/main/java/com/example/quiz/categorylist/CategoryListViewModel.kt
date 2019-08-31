package com.example.quiz.categorylist

import com.example.quiz.data.remote.PopulateData
import com.example.quiz.framework.BaseViewModel

class CategoryListViewModel : BaseViewModel() {

    val categoryList = categoryDao.getAll()

    fun downloadAll() {
        PopulateData.downloadAllData()
    }

    override fun onCleared() {
        super.onCleared()
        PopulateData.cleanUp()
    }
}