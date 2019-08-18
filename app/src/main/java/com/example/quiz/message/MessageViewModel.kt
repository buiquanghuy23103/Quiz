package com.example.quiz.message

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.quiz.firebase.ANONYMOUS
import com.example.quiz.firebase.FirebaseAuthUtil
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Message

class MessageViewModel : BaseViewModel() {
    private var username = ANONYMOUS
    private val defaultMessageList = mutableListOf<Message>()
    val messageList = MutableLiveData(defaultMessageList)
    private val firebaseDatabaseUtil = FirebaseDatabaseUtil()
    private val firebaseAuthUtil = FirebaseAuthUtil()

    fun setFirebaseDatabaseUtilListener(listener: FirebaseDatabaseUtil.Listener) {
        firebaseDatabaseUtil.listener = listener
    }

    fun setFirebaseAuthUtilListener(listener: FirebaseAuthUtil.Listener) {
        firebaseAuthUtil.listener = listener
    }

    fun initFirebase() {
        initMessageEvent()
        initAuthState()
    }

    private fun initMessageEvent() {
        firebaseDatabaseUtil.attachMessageEventListener()
    }

    private fun initAuthState() {
        firebaseAuthUtil.setupAuthStateListener(
            { username -> onSignIn(username) },
            { authIntent -> onSignOut(authIntent) }
        )
    }

    private fun onSignIn(username: String) {
        this.username = username
        firebaseDatabaseUtil.attachMessageEventListener()
    }

    private fun onSignOut(authIntent: Intent) {
        firebaseDatabaseUtil.detachMessageEventListener()
        eraseMessageList()
        username = ANONYMOUS
        firebaseAuthUtil.listener.startAuthUI(authIntent)
    }

    private fun eraseMessageList() {
        val currentMessageList = messageList.value ?: defaultMessageList
        currentMessageList.clear()
        messageList.value = currentMessageList
    }

    fun sendMessage(message: String) {
        val newMessage = Message(text = message, username = this.username)
        sendMessageToFirebase(newMessage)
    }

    private fun sendMessageToFirebase(message: Message) {
        firebaseDatabaseUtil.sendMessage(message)
    }

    fun sendMessageToList(message: Message) {
        val currentMessageList = messageList.value ?: defaultMessageList
        currentMessageList.add(message)
        messageList.value = currentMessageList
    }
}