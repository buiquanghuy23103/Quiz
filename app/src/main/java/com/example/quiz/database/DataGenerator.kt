package com.example.quiz.database

import com.example.quiz.R
import com.example.quiz.model.Quiz

class DataGenerator {
    companion object{
        val quizzes = listOf(
            Quiz(R.string.question_africa, false),
            Quiz(R.string.question_australia, true),
            Quiz(R.string.question_oceans, true),
            Quiz(R.string.question_mideast, false),
            Quiz(R.string.question_americas, true),
            Quiz(R.string.question_asia, true)
        )
    }
}