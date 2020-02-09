package com.example.quiz.model

import androidx.room.Entity

@Entity(
    primaryKeys = ["userId", "categoryId"]
)
data class Score(
    var userId: String = "",
    var categoryId: Int = -1,
    var score: Int = 0
)