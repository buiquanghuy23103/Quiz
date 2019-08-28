package com.example.quiz.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.quiz.data.remote.FirebaseFetch
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class CategoryListViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()
    val categoryList = categoryDao.getAll()
    val quizList = quizDao.getAll()
    val choiceList = choiceDao.getAll()

    fun isCategoryListNull(): LiveData<Boolean> {
        return Transformations.map(categoryList) {
            it.isNullOrEmpty()
        }
    }

    fun isQuizListNull(): LiveData<Boolean> {
        return Transformations.map(quizList) {
            it.isNullOrEmpty()
        }
    }

    fun isChoiceListNull(): LiveData<Boolean> {
        return Transformations.map(choiceList) {
            it.isNullOrEmpty()
        }
    }

    fun downloadCategoryList() {
        FirebaseFetch.downloadData(categoryDao, Category::class.java)
            .subscribe {
                Timber.i("data is saved in local db")
            }.addTo(disposables)
    }

    fun downloadQuizList() {
        FirebaseFetch.downloadData(quizDao, Quiz::class.java)
            .subscribe {
                Timber.i("data is saved in local db")
            }.addTo(disposables)
    }

    fun downloadChoiceList() {
        FirebaseFetch.downloadData(choiceDao, Choice::class.java)
            .subscribe {
                Timber.i("data is saved in local db")
            }.addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}