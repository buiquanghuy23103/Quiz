package com.example.quiz.quizaskpager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.quiz.BaseViewModel
import com.example.quiz.database.DataRepository

class QuizAskPagerViewModel: BaseViewModel() {
    val quizIdList = repository.getQuizIdList()
}
