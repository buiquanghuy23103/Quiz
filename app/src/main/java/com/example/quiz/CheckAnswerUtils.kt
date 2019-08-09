package com.example.quiz

import com.example.quiz.model.Choice

class CheckAnswerUtils(choiceList: List<Choice>) {
    private val answerAssessment = choiceList.map { answer -> answer.isTrue }.toMutableList()

    fun saveUserSelectionAtPosition(position: Int) {
        println("🎁 answerAssessment = $answerAssessment, position = $position")
        answerAssessment[position] = answerAssessment[position].not()
        println("👇 answerAssessment = $answerAssessment")
    }

    fun result(): Boolean = answerAssessment.all { answerResult -> answerResult == true }
}