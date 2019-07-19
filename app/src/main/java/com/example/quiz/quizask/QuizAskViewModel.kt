package com.example.quiz.quizask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizAskViewModel(app: Application, private var index: Int) : AndroidViewModel(app) {
    private val repository = DataRepository(app)

    private val backgroundJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    private lateinit var quizIdList: List<Int>
    var currentQuizId = 0
    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        ioScope.launch {
            initQuiz()
            currentQuizId = quizIdList[index]
        }
    }

    private fun initQuiz() {
        quizIdList = repository.getAllQuizIds()
        _quiz.postValue(repository.getQuizById(quizIdList[index]))
    }

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}