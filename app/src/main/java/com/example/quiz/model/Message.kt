package com.example.quiz.model

data class Message(
    val username: String = "ANONYMOUS",
    var text: String = "No text",
    var photoUrl: String = ""
)