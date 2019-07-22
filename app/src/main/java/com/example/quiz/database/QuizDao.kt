package com.example.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.database.DbScheme.QuizTable
import com.example.quiz.model.Quiz

@Dao
interface QuizDao {
    @Insert
    fun insertAll(quizzes: List<Quiz>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(quiz: Quiz)

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME)
    fun getAllQuizzes(): LiveData<List<Quiz>>

    @Query("SELECT " + QuizTable.Cols.ID + " FROM " + QuizTable.TABLE_NAME)
    fun getIdList(): LiveData<List<Int>>

    @Query("SELECT * FROM " + QuizTable.TABLE_NAME + " WHERE " + QuizTable.Cols.ID + " = :id")
    fun getQuiz(id: Int): LiveData<Quiz>
}