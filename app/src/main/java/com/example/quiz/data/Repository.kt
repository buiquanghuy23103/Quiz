package com.example.quiz.data

import com.example.quiz.data.local.AppDatabase
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import javax.inject.Inject

class Repository @Inject constructor(database: AppDatabase) {
    private val quizDao = database.quizDao
    private val answerDao = database.answerDao

    fun saveQuiz(quiz: Quiz){
        quizDao.save(quiz)
    }

    fun saveAnswerList(answerList: List<Answer>){
        answerDao.saveList(answerList)
    }

    fun getAllQuizzes(): List<Quiz> {
        return quizDao.getAll()
    }

    fun getQuizIdList(): List<Int> {
        return quizDao.getIdList()
    }

    fun getQuizById(id: Int): Quiz {
        return quizDao.getById(id)
    }

    fun getAnswersByQuizId(id: Int): List<Answer> {
        return answerDao.getAnswersByQuizId(id)
    }

    fun deleteQuiz(quiz: Quiz){
        quizDao.delete(quiz)
    }
}