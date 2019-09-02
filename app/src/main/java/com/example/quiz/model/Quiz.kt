package com.example.quiz.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(
    tableName = "quizzes",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["categoryId"])]
)
data class Quiz(
    var text: String = "",
    var categoryId: Int = 0,
    var explanation: String? = null,
    var imageUrl: String = "https://www.vegetables.co.nz/assets/Uploads/vegetables.jpg",

    @PrimaryKey
    var id: Int = Random.nextInt()
)