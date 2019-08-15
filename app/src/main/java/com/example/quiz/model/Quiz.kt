package com.example.quiz.model

import androidx.room.*
import com.example.quiz.data.local.DbScheme.CategoryTable
import com.example.quiz.data.local.DbScheme.QuizTable
import kotlin.random.Random

@Entity(
    tableName = QuizTable.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = [CategoryTable.Cols.ID],
        childColumns = [QuizTable.Cols.CATEGORY_ID],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = [QuizTable.Cols.CATEGORY_ID])]
)
data class Quiz(
    @ColumnInfo(name = QuizTable.Cols.TEXT)
    var text: String = "",

    @ColumnInfo(name = QuizTable.Cols.CATEGORY_ID)
    var category: Int = 0,

    @PrimaryKey
    @ColumnInfo(name = QuizTable.Cols.ID)
    var id: Int = Random.nextInt()
)