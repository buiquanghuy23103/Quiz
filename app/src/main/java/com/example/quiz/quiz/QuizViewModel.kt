package com.example.quiz.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel

class QuizViewModel(quizId: String) : BaseViewModel() {

    val quiz = quizDao.getById(quizId)

    private val _result = MutableLiveData<Boolean>(false)
    val result: LiveData<Boolean> = _result

    fun markAsCorrectAnswer() {
        _result.value = true
    }

    fun markAsIncorrectAnswer() {
        _result.value = false
    }
}