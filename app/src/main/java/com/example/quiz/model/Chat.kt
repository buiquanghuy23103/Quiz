package com.example.quiz.model

import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date

data class Chat(
    val name: String = "unknown",
    val message: String = "default message",
    val uid: String = "a123",
    @ServerTimestamp val timeStamp: Date = Date.getDefaultInstance()
)