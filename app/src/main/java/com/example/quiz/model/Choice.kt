package com.example.quiz.model

import androidx.room.*
import com.example.quiz.data.local.DbScheme.ChoiceTable
import com.example.quiz.data.local.DbScheme.QuizTable
import kotlin.random.Random

@Entity(
    tableName = ChoiceTable.TABLE_NAME,
        foreignKeys = [ForeignKey(
            entity = Quiz::class,
            parentColumns = [QuizTable.Cols.ID],
            childColumns = [ChoiceTable.Cols.QUIZ_ID],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = [ChoiceTable.Cols.QUIZ_ID])]
    )
data class Choice(
    @ColumnInfo(name = ChoiceTable.Cols.QUIZ_ID)
    var quizId : Int,

    @ColumnInfo(name = ChoiceTable.Cols.TEXT)
    var text: String,

    @ColumnInfo(name = ChoiceTable.Cols.IS_TRUE)
    var isTrue: Boolean,

    @PrimaryKey
    @ColumnInfo(name = ChoiceTable.Cols.ID)
    var id: Int = Random.nextInt()
)