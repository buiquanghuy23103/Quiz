package com.example.quiz.model

data class Message(
    val userId: Int = 0,
    var text: String = "No text",
    var photoUrl: String = "https://static.thenounproject.com/png/340719-200.png"
)