package com.example.quiz.quizview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class QuizViewViewModelFactory(private val index: Int): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewViewModel::class.java)){
            return QuizViewViewModel(index) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}