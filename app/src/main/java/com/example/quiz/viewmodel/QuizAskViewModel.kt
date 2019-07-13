package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.database.DataGenerator
import com.example.quiz.model.Quiz
import kotlin.math.abs

class QuizAskViewModel(private val app: Application, private var index: Int) : AndroidViewModel(app) {
    private val TAG = "QuizAskViewModel"

    private val quizBank = DataGenerator.quizzes
    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        updateQuiz()
    }

    private fun updateQuiz(){
        _quiz.value = quizBank[index]
    }

    fun moveBack(){
        index = abs(index - 1) % quizBank.size
        updateQuiz()
    }

    fun moveForward(){
        index = (index + 1) % quizBank.size
        updateQuiz()
    }
}