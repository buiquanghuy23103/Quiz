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
    var quizId : Int,
    var text: String,
    var isTrue: Boolean,

    @PrimaryKey
    var id: Int = Random.nextInt()
)