package com.example.quiz.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.quiz.data.remote.PopulateData
import com.example.quiz.framework.BaseViewModel

class CategoryListViewModel : BaseViewModel() {

    val categoryList = categoryDao.getAll()

    fun isCategoryListNull(): LiveData<Boolean> {
        return Transformations.map(categoryList) {
            it.isNullOrEmpty()
        }
    }

    fun downloadAll() {
        PopulateData.downloadAllFromFirebase()
    }

    override fun onCleared() {
        super.onCleared()
        PopulateData.cleanUp()
    }
}