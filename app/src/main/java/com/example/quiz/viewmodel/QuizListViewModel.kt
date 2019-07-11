package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.R
import com.example.quiz.database.DataGenerator
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz

class QuizListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataRepository(application)
    val quizBank = repository.getAllQuizzes()
}