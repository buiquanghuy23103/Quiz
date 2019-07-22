package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.quiz.database.DbScheme.AnswerTable
import com.example.quiz.database.DbScheme.QuizTable
import kotlin.random.Random

@Entity(tableName = AnswerTable.TABLE_NAME,
        foreignKeys = [ForeignKey(
            entity = Quiz::class,
            parentColumns = [QuizTable.Cols.ID],
            childColumns = [AnswerTable.Cols.QUIZ_ID],
            onDelete = ForeignKey.CASCADE
        )],
        indices = [Index(value = [AnswerTable.Cols.QUIZ_ID])]
    )
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