package com.example.quiz.quizlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    private val _quizList = MutableLiveData<List<Quiz>>()
    val quizList: LiveData<List<Quiz>>
        get() = _quizList

    init {
        ioScope.launch { loadQuizList() }
    }

    private fun loadQuizList() {
        _quizList.postValue(quizDao.getAll())
    }
}