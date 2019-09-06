package com.example.quiz.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Chat(
    var userUid: String = "unknown",
    var message: String? = "default message",
    var photoUrl: String? = "",
    @ServerTimestamp var timestamp: Date? = null
)