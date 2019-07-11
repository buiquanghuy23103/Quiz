package com.example.quiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quiz.R
import com.example.quiz.database.DataGenerator
import com.example.quiz.model.Quiz

class QuizAskViewModel : ViewModel() {
    val quizBank = DataGenerator.quizzes

    var currentIndex : Int = 0
}