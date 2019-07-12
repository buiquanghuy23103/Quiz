package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class QuizAskViewModelFactory(private val app: Application, private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizAskViewModel::class.java)){
            return QuizAskViewModel(app, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}