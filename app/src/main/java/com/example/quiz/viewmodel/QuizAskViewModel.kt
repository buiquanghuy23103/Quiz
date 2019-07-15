package com.example.quiz.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.database.DataGenerator
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*
import kotlin.math.abs

class QuizAskViewModel(private val app: Application, private var index: Int) : AndroidViewModel(app) {
    private val TAG = "QuizAskViewModel"

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // TODO: Use RxJava for Room
    // TODO: Retrieve List<quiz.id> instead of List<Quiz>, then retrieve quiz using id
    private lateinit var quizBank: List<Quiz>
    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        uiScope.launch {
            val backgroundJob = CoroutineScope(Dispatchers.IO).launch { initQuizzes() }
            backgroundJob.join() // wait until background job is done
            Log.i(TAG, "Background done")
            updateQuiz()
        }
    }

    private fun initQuizzes() {
        quizBank = DataRepository(app).getAllQuizzesSync()
    }

    private fun updateQuiz(){
        _quiz.value = quizBank[index]
        Log.i(TAG, "currentIndex = " + index)
    }

    fun moveBack(){
        index = abs(index - 1) % quizBank.size
        updateQuiz()
    }

    fun moveForward(){
        index = (index + 1) % quizBank.size
        updateQuiz()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}