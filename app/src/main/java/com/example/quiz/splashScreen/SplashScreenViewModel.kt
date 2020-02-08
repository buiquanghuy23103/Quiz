package com.example.quiz.splashScreen

import androidx.lifecycle.ViewModel
import com.example.quiz.data.remote.PopulateData

class SplashScreenViewModel : ViewModel() {


    fun downloadAll() {
        PopulateData.downloadAllData()
    }

    override fun onCleared() {
        super.onCleared()
        PopulateData.cleanUp()
    }

}