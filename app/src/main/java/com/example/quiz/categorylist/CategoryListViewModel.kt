package com.example.quiz.categorylist

import androidx.lifecycle.MutableLiveData
import com.example.quiz.data.remote.FirebaseFetch
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Category
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class CategoryListViewModel : BaseViewModel() {

    private val firebaseFetch = FirebaseFetch(Category::class.java)
    private val categoryListObservable = Observable.concat(
        categoryDao.getAllAsObservable(),
        firebaseFetch.dataList
    )
    private val disposables = CompositeDisposable()
    private val _categoryList = MutableLiveData<List<Category>>()
    init {
        categoryListObservable
            .subscribe {
            _categoryList.postValue(it)
            Timber.i("Category list updated: $it")
        }.addTo(disposables)
    }
    val categoryList = categoryDao.getAll()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        firebaseFetch.cleanUp()
    }
}