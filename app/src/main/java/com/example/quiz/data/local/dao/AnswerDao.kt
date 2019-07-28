package com.example.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.data.local.DbScheme.AnswerTable
import com.example.quiz.model.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveList(answerList: List<Answer>)

    @Query("SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.Cols.QUIZ_ID + " = :quizId")
    suspend fun getAnswersByQuizId(quizId: Int): List<Answer>
}