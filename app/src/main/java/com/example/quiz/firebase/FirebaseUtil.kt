package com.example.quiz.firebase

import android.content.Context
import android.net.Uri
import com.example.quiz.model.Chat
import timber.log.Timber

class FirebaseUtil(private val listener: Listener) {
    private val currentUserProfile = FirebaseAuthUtil.userProfile

    init {
        initFirebaseAuth()
    }

    private fun initFirebaseAuth() {
        FirebaseAuthUtil.listener = listener as FirebaseAuthUtil.Listener
        FirebaseAuthUtil.setupAuthStateListener()
    }

    fun sendMessage(text: String) {
        val newMessage = Chat(currentUserProfile.uid, text)
        FirestoreUtil.sendMessage(newMessage)
    }

    fun signOut(context: Context) {
        FirebaseAuthUtil.signOut(context)
    }

    fun uploadSelectedPhoto(selectedPhotoUri: Uri) {
        FirebaseStorageUtil.uploadSelectedPhoto(selectedPhotoUri)
        { photoUrl -> uploadPhotoUrl(photoUrl) }
    }

    private fun uploadPhotoUrl(photoUrl: String) {
        Timber.i("userProfile uid = ${currentUserProfile.uid}")
        val newMessage = Chat(currentUserProfile.uid, "", photoUrl)
        FirestoreUtil.sendMessage(newMessage)
    }

    fun cleanUp() {
        FirebaseAuthUtil.cleanUp()
    }

    interface Listener
}