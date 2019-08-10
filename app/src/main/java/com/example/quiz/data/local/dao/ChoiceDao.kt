package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.data.local.DbScheme.AnswerTable
import com.example.quiz.model.Choice

@Dao
interface ChoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(choiceList: List<Choice>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(choice: Choice): Long

    @Query("SELECT * FROM ${AnswerTable.TABLE_NAME} WHERE ${AnswerTable.Cols.ID} = :answerId")
    fun getById(answerId: Int): LiveData<Choice>

    @Query("SELECT * FROM ${AnswerTable.TABLE_NAME} WHERE ${AnswerTable.Cols.QUIZ_ID} = :quizId")
    fun getChoicesByQuizId(quizId: Int): LiveData<List<Choice>>
}