package com.example.quiz.database

import com.example.quiz.R
import com.example.quiz.model.Quiz

class DataGenerator {
    companion object{
        val quizzes = listOf(
            Quiz("Canberra is the capital of Australia.", false),
            Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", true),
            Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", true),
            Quiz("The source of the Nile River is in Egypt.", false),
            Quiz("The Amazon River is the longest river in the Americas.", true),
            Quiz("Lake Baikal is the world\\'s oldest and deepest freshwater lake.", true)
        )
    }
}