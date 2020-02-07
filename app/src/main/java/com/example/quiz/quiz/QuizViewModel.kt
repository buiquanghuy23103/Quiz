package com.example.quiz.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel

class QuizViewModel(quizId: Int) : BaseViewModel() {

    val quiz = quizDao.getById(quizId)
    val choiceList = choiceDao.getChoicesByQuizId(quizId)
    private val choiceAssessmentUtil = ChoiceAssessmentUtil(choiceList)

    private val _result = MutableLiveData<Boolean>(false)
    val result: LiveData<Boolean> = _result

    fun markAsCorrectAnswer() {
        _result.value = true
    }

    fun markAsIncorrectAnswer() {
        _result.value = false
    }

    val assessment = choiceAssessmentUtil.assessment

    fun toggleChoiceChosenById(choiceId: Int) =
        choiceAssessmentUtil.toggleChoiceChosenById(choiceId)

    fun getChosenStateById(choiceId: Int) = choiceAssessmentUtil.getChosenStateById(choiceId)
}