package com.example.quiz

import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

val testAnswerList = listOf(
    Choice(1, "answer 0", true),
    Choice(1, "answer 1", false),
    Choice(1, "answer 2", true),
    Choice(1, "answer 3", false)
)

val sampleQuizList = listOf(
    Quiz("Canberra is the capital of Australia.", 0),
    Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", 1),
    Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", 2),
    Quiz("The source of the Nile River is in Egypt.", 3),
    Quiz("The Amazon River is the longest river in the Americas.", 4),
    Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.", 5)
).sortedBy { quiz -> quiz.text }