package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(quizList: List<Quiz>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(quiz: Quiz): Long

    @Query("SELECT * FROM ${QuizTable.TABLE_NAME} ORDER BY ${QuizTable.Cols.QUESTION}")
    fun getAll(): LiveData<List<Quiz>>

    @Query("SELECT * FROM ${QuizTable.TABLE_NAME} WHERE ${QuizTable.Cols.ID} = :id")
    fun getById(id: Int): LiveData<Quiz>

    @Query("SELECT ${QuizTable.Cols.ID} FROM ${QuizTable.TABLE_NAME} WHERE ${QuizTable.Cols.CATEGORY} = :category")
    fun getQuizIdListByCategory(category: String): List<Int>
}