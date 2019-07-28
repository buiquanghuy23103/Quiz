package com.example.quiz.data.local.dao

import androidx.room.*
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveList(quizList: List<Quiz>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(quiz: Quiz)

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME)
    suspend fun getAll(): List<Quiz>

    @Query("SELECT " + QuizTable.Cols.ID + " FROM " + QuizTable.TABLE_NAME)
    suspend fun getIdList(): List<Int>

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME + " WHERE " + QuizTable.Cols.ID + " = :id")
    suspend fun getById(id: Int): Quiz

    @Delete
    suspend fun delete(quiz: Quiz)
}