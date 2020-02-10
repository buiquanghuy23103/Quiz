package com.example.quiz.data.remote

import com.example.quiz.dagger.Injector
import com.example.quiz.model.Category
import com.example.quiz.model.Quiz
import com.example.quiz.model.Score
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

object PopulateData {
    private val db = Injector.get().appDatabase()
    private val compositeDisposable = CompositeDisposable()
    private val categoryFetch = FirebaseFetch(db.categoryDao, Category::class.java)
    private val quizFetch = FirebaseFetch(db.quizDao, Quiz::class.java)
    private val scoreFetch = FirebaseFetch(db.scoreDao, Score::class.java)

    fun downloadAllData() {
        categoryFetch.downloadData()
            .concatWith(quizFetch.downloadData())
            .concatWith(scoreFetch.downloadData())
            .subscribeBy(
                onComplete = { Timber.i("All data downloaded") },
                onError = { Timber.e(it) }
            )
            .addTo(compositeDisposable)
    }

    fun cleanUp() {
        compositeDisposable.dispose()
    }
}