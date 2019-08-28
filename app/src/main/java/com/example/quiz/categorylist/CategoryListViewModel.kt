package com.example.quiz.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.quiz.data.remote.FirebaseFetch
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import io.reactivex.disposables.CompositeDisposable

class CategoryListViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()
    private val categoryFetch = FirebaseFetch(categoryDao, Category::class.java)
    private val quizFetch = FirebaseFetch(quizDao, Quiz::class.java)
    private val choiceFetch = FirebaseFetch(choiceDao, Choice::class.java)
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
        categoryFetch.downloadData()
    }

    fun downloadQuizList() {
        quizFetch.downloadData()
    }

    fun downloadChoiceList() {
        choiceFetch.downloadData()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        categoryFetch.cleanUp()
        quizFetch.cleanUp()
        choiceFetch.cleanUp()
    }
}