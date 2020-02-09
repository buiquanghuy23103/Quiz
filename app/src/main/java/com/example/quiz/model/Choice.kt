package com.example.quiz.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(
    tableName = "choices",
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["id"],
        childColumns = ["quizId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["quizId"])]
)
data class Choice(
    var quizId: Int = 0,
    var text: String = "Default text",
    var correct: Boolean = false,

    @PrimaryKey
    var id: Int = Random.nextInt()
): BaseModel()