package com.example.quiz.model

import androidx.room.Embedded
import androidx.room.Relation

data class Achievement(

    @Embedded
    val category: Category,

    @Relation(
        entity = Quiz::class,
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val quizzes: List<Quiz>,

    @Relation(
        entity = Score::class,
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val scores: List<Score>

)