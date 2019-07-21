package com.example.quiz.quizlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository
import com.example.quiz.model.Quiz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    val quizBank = repository.getAllQuizzes()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun save(quiz: Quiz){
        ioScope.launch { repository.save(quiz) }
    }
}