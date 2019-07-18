package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class QuizEditViewModelFactory(private val app: Application, private val index: Int): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizEditViewModel::class.java)){
            return QuizEditViewModel(app, index) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}