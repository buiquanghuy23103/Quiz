package com.example.quiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.quiz.R
import com.example.quiz.database.DataGenerator
import com.example.quiz.model.Quiz

class QuizAskViewModel(private val app: Application, private val id: Int) : AndroidViewModel(app) {
    val quizBank = DataGenerator.quizzes

    var currentIndex : Int = 0

}