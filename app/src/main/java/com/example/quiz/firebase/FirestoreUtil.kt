package com.example.quiz.firebase

import com.example.quiz.model.Chat
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtil {

    fun sendMessage(chat: Chat) {
        val db = FirebaseFirestore.getInstance()
        val chatRef = db.collection("Chat")
        chatRef.add(chat)
    }

}