package com.example.quiz.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quiz.model.Quiz
import com.example.quiz.ui.QuizListAdapter

class DataRepository(application: Application) {
    private val database = AppDatabase.getInstance(application.applicationContext)
    private val quizDao = database.quizDao

    fun saveQuiz(quiz: Quiz){
        return quizDao.saveQuiz(quiz)
    }

    fun getAllQuizzes(): LiveData<List<Quiz>>{
        return quizDao.getAllQuizzes()
    }

    fun getAllQuizIds(): List<Int>{
        return quizDao.getAllQuizIds()
    }

    fun getQuiz(id: Int): Quiz{
        return quizDao.getQuiz(id)
    }
}