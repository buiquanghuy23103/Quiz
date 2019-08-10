package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quiz.data.local.DbScheme.QuizTable
import com.example.quiz.framework.BaseData
import kotlin.random.Random

@Entity(tableName = QuizTable.TABLE_NAME)
data class Quiz(
    @ColumnInfo(name = QuizTable.Cols.QUESTION)
    override var text: String = "",

    @ColumnInfo(name = QuizTable.Cols.CATEGORY)
    var category: String = "Miscellaneous",

    @PrimaryKey
    @ColumnInfo(name = QuizTable.Cols.ID)
    override var id: Int = Random.nextInt()
) : BaseData