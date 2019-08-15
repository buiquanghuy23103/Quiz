package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(quizList: List<Quiz>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(quiz: Quiz): Long

    @Query("SELECT * FROM quizzes ORDER BY text")
    fun getAll(): LiveData<List<Quiz>>

    @Query("SELECT * FROM quizzes WHERE id = :id")
    fun getById(id: Int): LiveData<Quiz>

    @Query("SELECT id FROM quizzes WHERE categoryId = :categoryId")
    fun getQuizIdListByCategory(categoryId: Int): LiveData<List<Int>>
}