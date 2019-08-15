package com.example.quiz.model

import kotlin.random.Random

data class Category(
    var id: Int = Random.nextInt(),
    var text: String = "",
    var imageUrl: String = ""
)