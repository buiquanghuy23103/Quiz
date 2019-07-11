package com.example.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.database.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Query("SELECT * FROM " + QuizTable.TABLE_NAME)
    fun getAllQuizzes(): LiveData<List<Quiz>>

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME + " WHERE " + QuizTable.Cols.ID + " = :id")
    fun getQuiz(id: Int): LiveData<Quiz>
}