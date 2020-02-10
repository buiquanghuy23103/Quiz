package com.example.quiz.splashScreen

import androidx.lifecycle.ViewModel
import com.example.quiz.data.remote.DataDownloader
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val dataDownloader: DataDownloader
) : ViewModel() {

    fun isLoggedIn(): Boolean {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user != null
    }

    fun downloadAll() {
        dataDownloader.downloadAllData()
    }

    override fun onCleared() {
        super.onCleared()
        dataDownloader.cleanUp()
    }

}