package com.example.quiz.firebase

import android.content.Context
import android.net.Uri
import com.example.quiz.model.Chat
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class FirebaseUtil(private val listener: Listener) {
    private val currentUserProfile = FirebaseAuthUtil.userProfileSubject
        .filter { it.uid != "a123" }
    private val compositeDisposable = CompositeDisposable()

    init {
        initFirebaseAuth()
    }

    private fun initFirebaseAuth() {
        FirebaseAuthUtil.listener = listener as FirebaseAuthUtil.Listener
        FirebaseAuthUtil.setupAuthStateListener()
    }

    fun sendMessage(text: String) {
        currentUserProfile.subscribe { userProfile ->
            val newMessage = Chat(userProfile.uid, text)
            FirestoreUtil.sendMessage(newMessage)
        }.addTo(compositeDisposable)
    }

    fun signOut(context: Context) {
        FirebaseAuthUtil.signOut(context)
    }

    fun uploadSelectedPhoto(selectedPhotoUri: Uri) {
        FirebaseStorageUtil.uploadSelectedPhoto(selectedPhotoUri)
        { photoUrl -> uploadPhotoUrl(photoUrl) }
    }

    private fun uploadPhotoUrl(photoUrl: String) {
        currentUserProfile.subscribe { userProfile ->
            Timber.i("userProfile uid = ${userProfile.uid}")
            val newMessage = Chat(userProfile.uid, "", photoUrl)
            FirestoreUtil.sendMessage(newMessage)
        }.addTo(compositeDisposable)
    }

    fun cleanUp() {
        FirebaseAuthUtil.cleanUp()
    }

    interface Listener
}