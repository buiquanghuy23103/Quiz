package com.example.quiz

import com.example.quiz.model.Answer

class CheckAnswerUtils(answerList: List<Answer>) {
    private val answerAssessment: MutableList<Boolean>

    init {
        answerAssessment = answerList.map { answer -> answer.isTrue.not() } as MutableList<Boolean>
    }

    fun saveUserSelectionAtPosition(position: Int) {
        println("🎁 answerAssessment = $answerAssessment, position = $position")
        answerAssessment[position] = answerAssessment[position].not()
        println("👇 answerAssessment = $answerAssessment")
    }

    fun result(): Boolean = answerAssessment.all { answerResult -> answerResult == true }
}