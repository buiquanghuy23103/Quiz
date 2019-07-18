package com.example.quiz.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*
import kotlin.math.abs

class QuizAskViewModel(app: Application, private var index: Int) : AndroidViewModel(app) {
    private val TAG = "QuizAskViewModel"
    private val repository = DataRepository(app)

    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    private lateinit var quizIdList: List<Int>
    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        ioScope.launch {
            quizIdList = repository.getAllQuizIds()
            updateQuiz()
        }
    }

    private fun updateQuiz(){
        _quiz.postValue(repository.getQuiz(quizIdList[index]))
    }

    fun moveBack(){
        index = abs(index - 1) % quizIdList.size
        ioScope.launch { updateQuiz() }
    }

    fun moveForward(){
        index = (index + 1) % quizIdList.size
        ioScope.launch { updateQuiz() }
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}