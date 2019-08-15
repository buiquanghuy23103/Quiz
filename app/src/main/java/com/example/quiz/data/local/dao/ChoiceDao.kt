package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.model.Choice

@Dao
interface ChoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(choiceList: List<Choice>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(choice: Choice): Long

    @Query("SELECT * FROM choices WHERE id = :id")
    fun getById(id: Int): LiveData<Choice>

    @Query("SELECT * FROM choices WHERE quizId = :quizId")
    fun getChoicesByQuizId(quizId: Int): LiveData<List<Choice>>
}