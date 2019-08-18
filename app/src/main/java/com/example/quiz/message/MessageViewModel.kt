package com.example.quiz.message

import androidx.lifecycle.MutableLiveData
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Message

class MessageViewModel : BaseViewModel() {
    private var username = "anonymous"
    private val defaultMessageList = mutableListOf<Message>()
    val messageList = MutableLiveData(defaultMessageList)
    val firebaseDatabaseUtil = FirebaseDatabaseUtil()

    fun onSignInAsUsername() {
        firebaseDatabaseUtil.attachMessageEventListener()
    }

    fun sendMessage(message: String) {
        Message(text = message, username = this.username).let { message ->
            sendMessageToFirebase(message)
        }
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