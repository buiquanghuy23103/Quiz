package com.example.quiz.explanation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quiz.firebase.FirebaseUtil
import com.example.quiz.model.Message

class ExplanationViewModel(private val app: Application, listener: FirebaseUtil.Listener) :
    AndroidViewModel(app) {
    private val defaultMessageList = listOf<Message>()
    private val firebaseUtil = FirebaseUtil(listener)
    val messageList = MutableLiveData(defaultMessageList)

    fun sendMessage(text: String) {
        firebaseUtil.uploadMessage(text)
    }

    fun updateMessageList(newMessageList: List<Message>) {
        messageList.value = newMessageList
    }

    fun signOut() {
        firebaseUtil.signOut(app)
    }

    fun uploadImage(selectedPhotoUri: Uri) {
        firebaseUtil.uploadSelectedPhoto(selectedPhotoUri)
    }

    override fun onCleared() {
        super.onCleared()
        firebaseUtil.cleanUp()
    }
}