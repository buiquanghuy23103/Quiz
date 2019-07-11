package com.example.quiz.model

import androidx.room.Entity
import com.example.quiz.database.DbScheme
import kotlin.random.Random

@Entity(tableName = DbScheme.QuizTable.TABLE_NAME)
data class Quiz(
    var question : Int,
    var answer : Boolean
) {
   val id = Random.nextInt()
}