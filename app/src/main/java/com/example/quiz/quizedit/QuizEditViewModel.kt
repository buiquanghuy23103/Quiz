package com.example.quiz.quizedit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.*

class QuizEditViewModel(private val id: Int) : BaseViewModel() {
    val quiz: LiveData<Quiz>
    var quizSync = Quiz("No change")

    init {
        quiz = repository.getQuizById(id)
    }

    fun saveQuiz(){
        ioScope.launch { repository.save(quizSync) }
    }

}
