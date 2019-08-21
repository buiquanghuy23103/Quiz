package com.example.quiz.message

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quiz.firebase.FirebaseAuthUtil
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.firebase.FirebaseStorageUtil
import com.example.quiz.model.Message
import timber.log.Timber

class MessageViewModel(private val app: Application) : AndroidViewModel(app) {
    private val defaultMessageList = listOf<Message>()
    private val username: String
        get() = FirebaseAuthUtil.readUsername()
    val messageList = MutableLiveData(defaultMessageList)

    init {
        FirebaseAuthUtil.setupAuthStateListener()
        FirebaseDatabaseUtil.attachMessageEventListener()
    }

    fun setFirebaseDatabaseUtilListener(listener: FirebaseDatabaseUtil.Listener) {
        FirebaseDatabaseUtil.listener = listener
    }

    fun setFirebaseAuthUtilListener(listener: FirebaseAuthUtil.Listener) {
        FirebaseAuthUtil.listener = listener
    }

    fun sendMessage(message: String) {
        val newMessage = Message(text = message, username = username)
        FirebaseDatabaseUtil.sendMessage(newMessage)
    }

    fun updateMessageList(newMessageList: List<Message>) {
        messageList.value = newMessageList
    }

    fun signOut() {
        FirebaseAuthUtil.signOut(app)
    }

    fun uploadImage(selectedPhotoUri: Uri) {
        FirebaseStorageUtil.uploadSelectedPhoto(selectedPhotoUri) { photoUrl ->
            val newMessage =
                Message(username, null, photoUrl).also { Timber.i("photoUrl = $photoUrl") }
            FirebaseDatabaseUtil.sendMessage(newMessage)
        }
    }

    override fun onCleared() {
        super.onCleared()
        FirebaseDatabaseUtil.detachMessageEventListener()
    }
}