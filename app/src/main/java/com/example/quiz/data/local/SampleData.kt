package com.example.quiz.data.local

import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import java.util.*

object SampleData {
    val sampleQuizList = listOf(
        Quiz("Canberra is the capital of Australia."),
        Quiz("The Pacific Ocean is larger than the Atlantic Ocean."),
        Quiz("The Suez Canal connects the Red Sea and the Indian Ocean."),
        Quiz("The source of the Nile River is in Egypt."),
        Quiz("The Amazon River is the longest river in the Americas."),
        Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.")
    )

    val sampleAnswerList = generateAnswerList()

    private fun generateAnswerList(): List<Answer> {
        var list = ArrayList<Answer>()
        sampleQuizList.forEach {
            for (i in 1..4) {
                val answer = Answer(it.id, "Answer " + i, i % 2 == 0)
                list.add(answer)
            }
        }

        return list
    }
}