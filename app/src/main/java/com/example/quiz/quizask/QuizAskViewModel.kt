package com.example.quiz.quizask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizAskViewModel(private var index: Int) : BaseViewModel() {
    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    private lateinit var quizIdList: List<Int>
    var currentQuizId = 0
    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        initProp()
    }

    private fun initProp() {
        ioScope.launch {
            quizIdList = repository.getAllQuizIds()
            _quiz.postValue(repository.getQuizById(quizIdList[index]))
            currentQuizId = quizIdList[index]
        }
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}