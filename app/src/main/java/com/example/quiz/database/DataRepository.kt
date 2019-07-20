package com.example.quiz.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quiz.model.Quiz

class DataRepository(application: Application) {
    private val database = AppDatabase.getInstance(application.applicationContext)
    private val quizDao = database.quizDao

    fun save(quiz: Quiz){
        return quizDao.save(quiz)
    }

    fun getAllQuizzes(): LiveData<List<Quiz>>{
        return quizDao.getAllQuizzes()
    }

    fun getAllQuizIds(): List<Int>{
        return quizDao.getAllIds()
    }

    fun getQuizById(id: Int): Quiz{
        return quizDao.getQuiz(id)
    }
}