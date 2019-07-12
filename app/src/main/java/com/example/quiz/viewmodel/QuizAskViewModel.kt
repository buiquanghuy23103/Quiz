package com.example.quiz.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.database.DataGenerator
import com.example.quiz.model.Quiz
import kotlin.math.abs

class QuizAskViewModel(private val app: Application, private val id: Int) : AndroidViewModel(app) {
    private val TAG = "QuizAskViewModel"

    private val quizBank = DataGenerator.quizzes
    private var currentIndex : Int = 0

    private var _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    init {
        for (i in 0..(quizBank.size-1)){
            if (quizBank[i].id == id){
                currentIndex = i
                updateQuiz()
                Log.i(TAG, "currentIndex = " + i)
                break
            }
        }
    }

    private fun updateQuiz(){
        _quiz.value = quizBank[currentIndex]
    }

    fun moveBack(){
        currentIndex = abs(currentIndex - 1) % quizBank.size
        updateQuiz()
    }

    fun moveForward(){
        currentIndex = (currentIndex + 1) % quizBank.size
        updateQuiz()
    }
}