package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.data.local.dao.ScoreDao
import com.example.quiz.model.Category
import com.example.quiz.model.Quiz
import com.example.quiz.model.Score
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class DataDownloader @Inject constructor(
    categoryDao: CategoryDao,
    quizDao: QuizDao,
    scoreDao: ScoreDao,
    firestore: FirebaseFirestore
)

{
    private val compositeDisposable = CompositeDisposable()
    private val categoryFetch = FirebaseFetch(categoryDao, Category::class.java, firestore)
    private val quizFetch = FirebaseFetch(quizDao, Quiz::class.java, firestore)
    private val scoreFetch = FirebaseFetch(scoreDao, Score::class.java, firestore)

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