package com.example.quiz.model

import androidx.room.*
import com.example.quiz.data.local.DbScheme.AnswerTable
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.framework.BaseData
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
    @ColumnInfo(name = AnswerTable.Cols.QUIZ_ID)
    var quizId : Int,

    @ColumnInfo(name = AnswerTable.Cols.TEXT)
    override var text: String,

    @ColumnInfo(name = AnswerTable.Cols.IS_TRUE)
    var isTrue: Boolean,

    @ColumnInfo(name = AnswerTable.Cols.IS_CHOSEN)
    var isChosen: Boolean = false,

    @PrimaryKey
    @ColumnInfo(name = AnswerTable.Cols.ID)
    override var id: Int = Random.nextInt()
) : BaseData