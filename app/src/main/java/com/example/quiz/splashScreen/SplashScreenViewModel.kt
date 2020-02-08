package com.example.quiz.splashScreen

import androidx.lifecycle.ViewModel
import com.example.quiz.data.remote.PopulateData
import com.google.firebase.auth.FirebaseAuth

class SplashScreenViewModel : ViewModel() {

    fun isLoggedIn(): Boolean {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user != null
    }

    fun downloadAll() {
        PopulateData.downloadAllData()
    }

    override fun onCleared() {
        super.onCleared()
        PopulateData.cleanUp()
    }

}