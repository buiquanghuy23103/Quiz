package com.example.quiz.firebase

import android.content.Context
import android.net.Uri
import com.example.quiz.model.Message

class FirebaseUtil(private val listener: Listener) {
    private val username: String
        get() = FirebaseAuthUtil.readUsername()

    init {
        initFirebaseAuth()
        initFirebaseDatabase()
    }

    private fun initFirebaseAuth() {
        FirebaseAuthUtil.listener = listener as FirebaseAuthUtil.Listener
        FirebaseAuthUtil.setupAuthStateListener()
    }

    private fun initFirebaseDatabase() {
        FirebaseDatabaseUtil.listener = listener as FirebaseDatabaseUtil.Listener
        FirebaseDatabaseUtil.attachMessageEventListener()
    }

    fun uploadMessage(text: String) {
        val newMessage = Message(text = text, username = username)
        FirebaseDatabaseUtil.sendMessage(newMessage)
    }

    fun signOut(context: Context) {
        FirebaseAuthUtil.signOut(context)
    }

    fun uploadSelectedPhoto(selectedPhotoUri: Uri) {
        FirebaseStorageUtil.uploadSelectedPhoto(selectedPhotoUri)
        { photoUrl -> uploadPhotoUrl(photoUrl) }
    }

    private fun uploadPhotoUrl(photoUrl: String) {
        val newMessage = Message(username, null, photoUrl)
        FirebaseDatabaseUtil.sendMessage(newMessage)
    }

    fun cleanUp() {
        FirebaseDatabaseUtil.detachMessageEventListener()
    }

    interface Listener
}