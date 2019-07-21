package com.example.quiz.quizedit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository
import kotlinx.coroutines.*

class QuizEditViewModel(app: Application, private val id: Int) : BaseViewModel(app) {
    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    init {
        viewModelScope.launch {
            fetchQuizFromBackground().start()
        }
    }

    private fun fetchQuizFromBackground() = ioScope.async(start = CoroutineStart.LAZY) {
        repository.getQuizById(id)
    }

    val quiz = runBlocking { getQuiz() }
    suspend fun getQuiz() = fetchQuizFromBackground().await()

    fun saveQuiz(){
        ioScope.launch { repository.save(quiz) }
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}
