package com.example.quiz.data.remote

import com.example.quiz.dagger.Injector
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

object PopulateData {
    private val db = Injector.get().appDatabase()
    private val compositeDisposable = CompositeDisposable()
    private val categoryFetch = FirebaseFetch(db.categoryDao, Category::class.java, Category())
    private val quizFetch = FirebaseFetch(db.quizDao, Quiz::class.java, Quiz())
    private val choiceFetch = FirebaseFetch(db.choiceDao, Choice::class.java, Choice())


    fun downloadAllData() {
        categoryFetch.downloadData()
            .concatWith(quizFetch.downloadData())
            .concatWith(choiceFetch.downloadData())
            .subscribeBy(
                onComplete = { Timber.i("zz All complete") },
                onError = { Timber.e(it.localizedMessage) }
            )
            .addTo(compositeDisposable)
    }

    fun cleanUp() {
        compositeDisposable.dispose()
    }
}