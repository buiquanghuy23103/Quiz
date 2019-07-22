package com.example.quiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.database.DbScheme.AnswerTable
import com.example.quiz.model.Answer

@Dao
interface AnswerDao {
    @Insert
    fun insertAll(answerList: List<Answer>)

    @Query("SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.Cols.QUIZ_ID + " = :quizId")
    fun getAnswersByQuizId(quizId: Int): List<Answer>
}