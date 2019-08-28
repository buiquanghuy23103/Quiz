package com.example.quiz.categorylist

import com.example.quiz.data.remote.FirebaseFetch
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class CategoryListViewModel : BaseViewModel() {

    private val firebaseFetch = FirebaseFetch(Category::class.java)
    private val disposables = CompositeDisposable()
    val categoryList = categoryDao.getAll()

    init {
        loadCategoryList()
    }

    private fun loadCategoryList() {
        firebaseFetch.downloadData(categoryDao)
            .subscribe {
                Timber.i("data is saved in local db")
            }.addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}