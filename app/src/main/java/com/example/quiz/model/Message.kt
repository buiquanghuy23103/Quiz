package com.example.quiz.model

data class Message(
    val userId: Int = 0,
    var text: String = "No text",
    var photoUrl: String = "No photo URL"
)