package com.example.quiz.quiz

import androidx.lifecycle.Transformations
import com.example.quiz.framework.BaseViewModel
import kotlinx.coroutines.launch

class QuizViewModel(quizId: Int) : BaseViewModel() {
    val quiz = quizDao.getById(quizId)
    val choiceList = choiceDao.getChoiceByQuizId(quizId)
    val assessment = Transformations.map(choiceList) {
        it.map { choice -> choice.isChosen == choice.isTrue }
            .all { it == true }
    }

    fun toggleChoiceChosenById(answerId: Int) {
        val newChoiceList = choiceList.value
            ?.let { answerList ->
                answerList.find { answer -> answer.id == answerId }
                    ?.apply { isChosen = isChosen.not() }
                    ?: throw Exception("Choice not found")
            } ?: throw Exception("List of choices is null")

        ioScope.launch { choiceDao.save(newChoiceList) }
    }

    fun getChoiceById(answerId: Int) = choiceDao.getById(answerId)
}