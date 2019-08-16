package com.example.quiz.model

data class User(
    val id: Int = 0,
    var username: String = "ANONYMOUS",
    var photoUrl: String = "https://avatars.servers.getgo.com/2205256774854474505_medium.jpg"
)