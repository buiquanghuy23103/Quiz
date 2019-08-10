package com.example.quiz.data.local

import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import java.util.*

object SampleData {
    val sampleQuizList = listOf(
        Quiz("Canberra is the capital of Australia.", "Geography"),
        Quiz("The Pacific Ocean is larger than the Atlantic Ocean.", "Geography"),
        Quiz("The Suez Canal connects the Red Sea and the Indian Ocean.", "Geography"),
        Quiz("The source of the Nile River is in Egypt."),
        Quiz("The Amazon River is the longest river in the Americas."),
        Quiz("Lake Baikal is the world\'s oldest and deepest freshwater lake.")
    ).sortedBy { answer -> answer.text }

    val sampleAnswerList = generateAnswerList()

    private fun generateAnswerList(): List<Choice> {
        val list = ArrayList<Choice>()
        sampleQuizList.forEach {
            for (i in 0..3) {
                val answer = Choice(it.id, "Answer " + i, i % 2 == 0)
                list.add(answer)
            }
        }

        return list
    }
}