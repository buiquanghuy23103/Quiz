package com.example.quiz.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.data.local.dao.QuizDao
import javax.inject.Inject

class QuizViewModel @Inject constructor(
    private val quizDao: QuizDao
): ViewModel() {

    lateinit var quizId: String

    fun withId(quizId: String) {
        this.quizId = quizId
    }

    fun quiz() = quizDao.getById(quizId)

    private val _result = MutableLiveData<Boolean>(false)
    val result: LiveData<Boolean> = _result

    fun markAsCorrectAnswer() {
        _result.value = true
    }

    fun markAsIncorrectAnswer() {
        _result.value = false
    }
}