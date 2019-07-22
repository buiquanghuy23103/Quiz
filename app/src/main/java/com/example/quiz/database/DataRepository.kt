package com.example.quiz.database

import androidx.lifecycle.LiveData
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import javax.inject.Inject

class DataRepository @Inject constructor(database: AppDatabase) {
    private val quizDao = database.quizDao
    private val answerDao = database.answerDao

    fun save(quiz: Quiz){
        return quizDao.save(quiz)
    }

    fun getAllQuizzes(): LiveData<List<Quiz>>{
        return quizDao.getAllQuizzes()
    }

    fun getQuizIdList(): LiveData<List<Int>>{
        return quizDao.getIdList()
    }

    fun getQuizById(id: Int): LiveData<Quiz>{
        return quizDao.getQuiz(id)
    }

    fun getAnswersByQuizId(id: Int): LiveData<List<Answer>>{
        return answerDao.getAnswersByQuizId(id)
    }
}