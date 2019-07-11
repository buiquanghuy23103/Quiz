package com.example.quiz.model

import kotlin.random.Random


data class Quiz(
    var question : Int,
    var answer : Boolean
) {
   val id = Random.nextInt()
}