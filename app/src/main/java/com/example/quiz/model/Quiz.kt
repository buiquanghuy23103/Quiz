package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quiz.database.DbScheme.QuizTable
import kotlin.random.Random

@Entity(tableName = QuizTable.TABLE_NAME)
data class Quiz(
    @ColumnInfo(name = QuizTable.Cols.QUESTION)
    var question : String = "",

    @ColumnInfo(name = QuizTable.Cols.ANSWER)
    var answer : Boolean = true,

    @PrimaryKey
    @ColumnInfo(name = QuizTable.Cols.ID)
    var id: Int = Random.nextInt()
)