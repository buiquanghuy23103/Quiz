package com.example.quiz.util

import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

val sampleQuizList = listOf(
    Quiz("Canberra is the capital of Australia.", "Geography"),
    Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", "Geography"),
    Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", "Geography"),
    Quiz("The source of the Nile River is in Egypt."),
    Quiz("The Amazon River is the longest river in the Americas."),
    Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.")
).sortedBy { quiz -> quiz.text }

val sampleQuiz = sampleQuizList[0]
val sampleAnswersOfSampleQuiz = generateAnswerListForSampleQuiz()

private fun generateAnswerListForSampleQuiz(): List<Choice> {
    return mutableListOf<Choice>().also {
        for (i in 0..3) {
            val answer = Choice(sampleQuiz.id, "Answer " + i, i % 2 == 0)
            it.add(answer)
        }
    }
}