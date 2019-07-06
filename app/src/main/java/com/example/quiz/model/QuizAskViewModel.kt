package com.example.quiz.model

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.quiz.R
import com.example.quiz.database.Quiz

class QuizAskViewModel : ViewModel() {
    val quizBank = listOf<Quiz>(
        Quiz(R.string.question_africa, false),
        Quiz(R.string.question_australia, true),
        Quiz(R.string.question_oceans, true),
        Quiz(R.string.question_mideast, false),
        Quiz(R.string.question_americas, true),
        Quiz(R.string.question_asia, true)
    )

    var currentIndex : Int = 0
}