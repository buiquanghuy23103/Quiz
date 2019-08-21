package com.example.quiz.firebase

import com.example.quiz.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

object FirebaseDatabaseUtil {

    lateinit var listener: Listener
    private val db = FirebaseDatabase.getInstance()
    private val dbReference = db.reference.child("messages")

    private val defaultMessage = Message(text = "default message")

    private val valueEventListener by lazy { initValueEventListener() }

    private fun initValueEventListener(): ValueEventListener {
        return object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Timber.e("Error downloading messages from database: ${p0.toException()}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val messageList = p0.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Message::class.java) ?: defaultMessage
                }
                listener.replaceWithNewMessageList(messageList)
            }
        }
    }

    fun sendMessage(message: Message) {
        dbReference.push().setValue(message)
    }

    fun attachMessageEventListener() {
        dbReference.addValueEventListener(valueEventListener)
    }

    fun detachMessageEventListener() {
        dbReference.removeEventListener(valueEventListener)
    }
    interface Listener {
        fun replaceWithNewMessageList(messageList: List<Message>)
    }
}