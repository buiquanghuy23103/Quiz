package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.model.Quiz

@Dao
interface QuizDao : BaseDao<Quiz> {

    @Query("SELECT * FROM quizzes")
    fun getAll(): LiveData<List<Quiz>>

    @Query("SELECT * FROM quizzes WHERE id = :id")
    fun getById(id: String): LiveData<Quiz>

    @Query("SELECT id FROM quizzes WHERE categoryId = :categoryId")
    fun getQuizIdListByCategory(categoryId: String): LiveData<List<String>>
}