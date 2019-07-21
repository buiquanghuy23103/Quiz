package com.example.quiz.quizask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository

class QuizAskPagerViewModel: BaseViewModel() {
    val quizList = repository.getAllQuizzes()
}
