package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.data.local.DbScheme.AnswerTable
import com.example.quiz.model.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(answerList: List<Answer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(answer: Answer)

    @Query("SELECT * FROM ${AnswerTable.TABLE_NAME} WHERE ${AnswerTable.Cols.ID} = :answerId")
    fun getSyncById(answerId: Int): LiveData<Answer>

    @Query("SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.Cols.QUIZ_ID + " = :quizId")
    fun getAnswersByQuizId(quizId: Int): List<Answer>
}