package com.example.quiz.quizview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizViewViewModel(quizId: Int) : BaseViewModel() {
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz
    private val _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>>
        get() = _answerList

    init {
        ioScope.launch {
            _quiz.postValue(repository.getQuizById(quizId))
            _answerList.postValue(repository.getAnswersByQuizId(quizId))
        }
    }
}