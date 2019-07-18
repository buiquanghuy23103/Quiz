package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.R
import com.example.quiz.database.DataGenerator
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuizListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataRepository(application)
    val quizBank = repository.getAllQuizzes()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun saveQuiz(quiz: Quiz){
        ioScope.launch { repository.saveQuiz(quiz) }
    }
}