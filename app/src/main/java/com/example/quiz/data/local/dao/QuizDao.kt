package com.example.quiz.data.local.dao

import androidx.room.*
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(quizList: List<Quiz>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(quiz: Quiz): Long

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME)
    fun getAll(): List<Quiz>

    @Query("SELECT " + QuizTable.Cols.ID + " FROM " + QuizTable.TABLE_NAME)
    fun getIdList(): List<Int>

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME + " WHERE " + QuizTable.Cols.ID + " = :id")
    fun getById(id: Int): Quiz

    @Delete
    fun delete(quiz: Quiz)
}