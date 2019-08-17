package com.example.quiz

import com.example.quiz.model.Message
import com.example.quiz.model.User
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtil {
    private val db = FirebaseDatabase.getInstance()
    private val dbReference = db.reference.child("messages")

    private val defaultUser = User()
    private val defaultMessage = Message(userId = defaultUser.id)


    fun sendMessage(message: Message) {
        dbReference.push().setValue(message)
    }
}