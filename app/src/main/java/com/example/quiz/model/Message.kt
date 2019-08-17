package com.example.quiz.model

data class Message(
    val username: String = "anonymous",
    var text: String = "No text",
    var photoUrl: String = ""
)