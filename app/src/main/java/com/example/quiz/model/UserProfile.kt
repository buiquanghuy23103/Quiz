package com.example.quiz.model

data class UserProfile(
    var uid: String = "a123",
    var name: String = "unknown",
    var email: String = "email@gmail.com",
    var photoUrl: String = ""
)