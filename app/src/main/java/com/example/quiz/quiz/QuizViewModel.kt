package com.example.quiz.quiz

import com.example.quiz.framework.BaseViewModel

class QuizViewModel(quizId: Int) : BaseViewModel() {
    val quiz = quizDao.getById(quizId)
    val choiceList = choiceDao.getChoicesByQuizId(quizId)
    private val choiceAssessmentUtil = ChoiceAssessmentUtil(choiceList)

    val assessment = choiceAssessmentUtil.assessment

    fun toggleChoiceChosenById(choiceId: Int) =
        choiceAssessmentUtil.toggleChoiceChosenById(choiceId)

    fun getChoseStateById(choiceId: Int) = choiceAssessmentUtil.getChosenStateById(choiceId)
}