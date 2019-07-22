package com.example.quiz.model

import kotlin.random.Random

data class Answer(
    val id : Int = Random.nextInt(),
    var quizId : Int,
    var text: String,
    var isTrue: Boolean
)