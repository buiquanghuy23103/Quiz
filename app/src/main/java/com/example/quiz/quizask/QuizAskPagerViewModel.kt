package com.example.quiz.quizask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.database.DataRepository

class QuizAskPagerViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = DataRepository(app)
    val quizList = repository.getAllQuizzes()
}
