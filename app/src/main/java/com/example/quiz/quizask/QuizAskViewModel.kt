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

class QuizAskViewModel(private var id: Int) : BaseViewModel() {
    val quiz: LiveData<Quiz>
    val answerList: LiveData<List<Answer>>

    init {
        quiz = repository.getQuizById(id)
        answerList = repository.getAnswersByQuizId(id)
    }
}