package com.example.quiz.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.quiz.data.remote.FirebaseFetch
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class CategoryListViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()
    val categoryList = categoryDao.getAll()

    fun isCategoryListNull(): LiveData<Boolean> {
        return Transformations.map(categoryList) {
            it.isNullOrEmpty()
        }
    }

    fun downloadCategoryList() {
        FirebaseFetch.downloadData(categoryDao, Category::class.java)
            .subscribe {
                Timber.i("data is saved in local db")
            }.addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}