package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuizEditViewModel(app: Application, private val id: Int) : AndroidViewModel(app) {
    private val TAG = "QuizAskViewModel"
    private val repository = DataRepository(app)
    private var quiz = Quiz()

    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    init {
        ioScope.launch {
            quiz = repository.getQuiz(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}
