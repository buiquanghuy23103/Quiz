package com.example.quiz.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.quiz.model.Answer

@Dao
interface AnswerDao {
    @Insert
    suspend fun insertAll(answerList: List<Answer>)
}