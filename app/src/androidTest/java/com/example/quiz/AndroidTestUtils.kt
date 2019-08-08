package com.example.quiz

import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import java.util.*

val sampleQuizList = listOf(
    Quiz("Canberra is the capital of Australia.", 0),
    Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", 1),
    Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", 2),
    Quiz("The source of the Nile River is in Egypt.", 3),
    Quiz("The Amazon River is the longest river in the Americas.", 4),
    Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.", 5)
)

val sampleQuiz = sampleQuizList[0]


val sampleAnswerList = generateAnswerList()
val sampleAnswersOfSampleQuiz = sampleAnswerList.filter { answer -> answer.quizId == sampleQuiz.id }

private fun generateAnswerList(): List<Answer> {
    val list = ArrayList<Answer>()
    sampleQuizList.forEach {
        for (i in 1..4) {
            val answer = Answer(it.id, "Answer " + i, i % 2 == 0)
            list.add(answer)
        }
    }

    return list
}