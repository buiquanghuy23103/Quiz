package com.example.quiz.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizEditViewModel(app: Application, private val id: Int) : AndroidViewModel(app) {
    private val TAG = "QuizEditViewModel"
    private val repository = DataRepository(app)
    lateinit var quiz: Quiz

    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    init {
        runBlocking {
            val backgroundJob = ioScope.launch {
                quiz = repository.getQuiz(id)
            }
            backgroundJob.join()
        }
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}
