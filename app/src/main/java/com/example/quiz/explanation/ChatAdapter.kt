package com.example.quiz.explanation

import android.view.ViewGroup
import com.example.quiz.model.Chat
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ChatAdapter(private val listener: MessageListItem.Listener) :
    FirestoreRecyclerAdapter<Chat, MessageListItem>(options) {
    companion object {
        private val query = FirebaseFirestore.getInstance()
            .collection(Chat::class.java.simpleName)
            .orderBy("timestamp")

        val options = FirestoreRecyclerOptions.Builder<Chat>()
            .setQuery(query, Chat::class.java)
            .build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MessageListItem.fromViewGroupWithListener(parent, listener)

    override fun onBindViewHolder(p0: MessageListItem, p1: Int, p2: Chat) =
        p0.bind(p2)
}