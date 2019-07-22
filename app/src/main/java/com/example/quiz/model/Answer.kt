package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.quiz.database.DbScheme.AnswerTable
import kotlin.random.Random

@Entity(tableName = AnswerTable.TABLE_NAME)
data class Answer(
    @ColumnInfo(name = AnswerTable.Cols.ID)
    val id : Int = Random.nextInt(),

    @ColumnInfo(name = AnswerTable.Cols.QUIZ_ID)
    var quizId : Int,

    @ColumnInfo(name = AnswerTable.Cols.TEXT)
    var text: String,

    @ColumnInfo(name = AnswerTable.Cols.IS_TRUE)
    var isTrue: Boolean
)