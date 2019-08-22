package com.example.quiz.explanation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.dagger.Injector
import com.example.quiz.firebase.FirebaseUtil
import com.example.quiz.model.Message
import com.example.quiz.model.Quiz

class ExplanationViewModel(
    private val app: Application,
    listener: FirebaseUtil.Listener,
    private val quizId: Int
) :
    AndroidViewModel(app) {
    private val defaultMessageList = listOf<Message>()
    private val firebaseUtil = FirebaseUtil(listener)

    val quiz by lazy { getQuizById() }
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

    private fun getQuizById(): LiveData<Quiz> {
        val injector = Injector.get()
        val quizDao = injector.appDatabase().quizDao
        return quizDao.getById(quizId)
    }

    override fun onCleared() {
        super.onCleared()
        firebaseUtil.cleanUp()
    }
}