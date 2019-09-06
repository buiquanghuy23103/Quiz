package com.example.quiz.explanation

import android.view.ViewGroup
import com.example.quiz.model.Chat
import com.example.quiz.model.UserProfile
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ChatAdapter : FirestoreRecyclerAdapter<Chat, ChatHolder>(buildFirestoreOptions()) {
    var allUsers = listOf<UserProfile>()
    companion object {

        private fun buildFirestoreOptions(): FirestoreRecyclerOptions<Chat> {
            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection("Chat")
            val query = collectionRef.orderBy("timestamp")

            return FirestoreRecyclerOptions.Builder<Chat>()
                .setQuery(query, Chat::class.java)
                .build()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatHolder.from(parent)
    override fun onBindViewHolder(holder: ChatHolder, p1: Int, chat: Chat) {
        val userProfile = allUsers.find { it.uid == chat.userUid } ?: UserProfile()
        holder.bind(chat, userProfile)
    }
}