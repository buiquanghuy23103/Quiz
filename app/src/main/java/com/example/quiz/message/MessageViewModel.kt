package com.example.quiz.message

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quiz.firebase.ANONYMOUS
import com.example.quiz.firebase.FirebaseAuthUtil
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.firebase.FirebaseStorageUtil
import com.example.quiz.model.Message
import timber.log.Timber

class MessageViewModel(private val app: Application) : AndroidViewModel(app) {
    private var username = ANONYMOUS
    private val defaultMessageList = listOf<Message>()
    val messageList = MutableLiveData(defaultMessageList)

    fun setFirebaseDatabaseUtilListener(listener: FirebaseDatabaseUtil.Listener) {
        FirebaseDatabaseUtil.listener = listener
    }

    fun setFirebaseAuthUtilListener(listener: FirebaseAuthUtil.Listener) {
        FirebaseAuthUtil.listener = listener
    }

    fun initFirebase() {
        initMessageEvent()
        initAuthState()
    }

    private fun initMessageEvent() {
        FirebaseDatabaseUtil.attachMessageEventListener()
    }

    private fun initAuthState() {
        FirebaseAuthUtil.setupAuthStateListener(
            { username -> onSignIn(username) },
            { authIntent -> onSignOut(authIntent) }
        )
    }

    private fun onSignIn(username: String) {
        this.username = username
        FirebaseDatabaseUtil.attachMessageEventListener()
    }

    private fun onSignOut(authIntent: Intent) {
        FirebaseDatabaseUtil.detachMessageEventListener()
        eraseMessageList()
        username = ANONYMOUS
        FirebaseAuthUtil.listener.startAuthUI(authIntent)
    }

    private fun eraseMessageList() {
        messageList.value = defaultMessageList
    }

    fun sendMessage(message: String) {
        val newMessage = Message(text = message, username = this.username)
        sendMessageToFirebase(newMessage)
    }

    private fun sendMessageToFirebase(message: Message) {
        FirebaseDatabaseUtil.sendMessage(message)
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
}