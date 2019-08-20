package com.example.quiz.model

data class Message(
    val username: String = "anonymous",
    var text: String? = null,
    var photoUrl: String? = null
)