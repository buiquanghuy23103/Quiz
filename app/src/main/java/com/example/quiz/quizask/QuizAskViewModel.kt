package com.example.quiz.quizask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizAskViewModel(private var index: Int) : BaseViewModel() {
    private lateinit var quizIdList: List<Int>
    var currentQuizId = 0

    private var _quiz = MutableLiveData<Quiz>()
    private var _answerList = MutableLiveData<List<Answer>>()

    val quiz: LiveData<Quiz>
        get() = _quiz
    val answerList: LiveData<List<Answer>>
        get() = _answerList

    init {
        initProp()
    }

    private fun initProp() {
        ioScope.launch {
            quizIdList = repository.getAllQuizIds()
            currentQuizId = quizIdList[index]

            _quiz.postValue(repository.getQuizById(currentQuizId))
            _answerList.postValue(repository.getAnswersByQuizId(currentQuizId))
        }
    }
}