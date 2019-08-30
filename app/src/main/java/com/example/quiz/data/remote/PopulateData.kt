package com.example.quiz.data.remote

import com.example.quiz.dagger.Injector
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

object PopulateData {
    private val db = Injector.get().appDatabase()
    private val categoryFetch = FirebaseFetch(db.categoryDao, Category::class.java, Category())
    private val quizFetch = FirebaseFetch(db.quizDao, Quiz::class.java, Quiz())
    private val choiceFetch = FirebaseFetch(db.choiceDao, Choice::class.java, Choice())

    fun downloadAllFromFirebase() {
        categoryFetch.downloadData()
        quizFetch.downloadData()
        choiceFetch.downloadData()
    }

    fun cleanUp() {
        categoryFetch.cleanUp()
        quizFetch.cleanUp()
        choiceFetch.cleanUp()
    }
}