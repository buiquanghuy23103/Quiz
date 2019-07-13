package com.example.quiz.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quiz.model.Quiz

class DataRepository(val application: Application) {
    private val database = AppDatabase.getInstance(application.applicationContext)
    private val quizDao = database.quizDao

    fun getAllQuizzes(): LiveData<List<Quiz>>{
        return quizDao.getAllQuizzes()
    }

    fun getAllQuizzesSync(): List<Quiz>{
        return quizDao.getAllQuizzesSync()
    }
}