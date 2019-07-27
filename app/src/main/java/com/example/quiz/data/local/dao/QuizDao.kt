package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMany(quizzes: List<Quiz>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(quiz: Quiz)

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME)
    fun getAllQuizzes(): LiveData<List<Quiz>>

    @Query("SELECT " + QuizTable.Cols.ID + " FROM " + QuizTable.TABLE_NAME)
    fun getIdList(): LiveData<List<Int>>

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME + " WHERE " + QuizTable.Cols.ID + " = :id")
    fun getQuiz(id: Int): LiveData<Quiz>

    @Delete
    fun deleteQuiz(quiz: Quiz)
}