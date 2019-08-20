package com.example.quiz.firebase

import com.example.quiz.model.Message
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseUtil {

    lateinit var listener: Listener
    private val db = FirebaseDatabase.getInstance()
    private val dbReference = db.reference.child("messages")

    private val defaultMessage = Message(text = "default message")

    private lateinit var childEventListener: ChildEventListener


    fun sendMessage(message: Message) {
        dbReference.push().setValue(message)
    }

    fun attachMessageEventListener() {
        createMessageEventListener()
        dbReference.addChildEventListener(childEventListener)
    }

    private fun createMessageEventListener() {
        childEventListener = object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val newMessage = p0.getValue(Message::class.java) ?: defaultMessage
                listener.addMessageToList(newMessage)
            }

            override fun onCancelled(p0: DatabaseError) {}
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot) {}
        }
    }

    fun detachMessageEventListener() {
        dbReference.removeEventListener(childEventListener)
    }
    interface Listener {
        fun addMessageToList(newMessage: Message)
    }
}