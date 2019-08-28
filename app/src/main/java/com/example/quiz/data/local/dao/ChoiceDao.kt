package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.model.Choice

@Dao
interface ChoiceDao : BaseDao<Choice> {

    @Query("SELECT * FROM choices")
    fun getAll(): LiveData<List<Choice>>

    @Query("SELECT * FROM choices WHERE id = :id")
    fun getById(id: Int): LiveData<Choice>

    @Query("SELECT * FROM choices WHERE quizId = :quizId")
    fun getChoicesByQuizId(quizId: Int): LiveData<List<Choice>>
}