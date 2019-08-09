package com.example.quiz.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.CheckAnswerUtils
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizViewModel(quizId: Int) : BaseViewModel() {
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz
    private val _choiceList = MutableLiveData<List<Choice>>()
    val choiceList: LiveData<List<Choice>>
        get() = _choiceList
    private lateinit var checkAnswerUtils: CheckAnswerUtils

    init {
        ioScope.launch {
            _quiz.postValue(quizDao.getById(quizId))

            val answerList = answerDao.getAnswersByQuizId(quizId)
            _choiceList.postValue(answerList)
            checkAnswerUtils = CheckAnswerUtils(answerList)
        }
    }

    fun isCorrectAnswer(): Boolean = checkAnswerUtils.result()

    fun toggleChoiceChosenById(answerId: Int) {
        val newAnswer = _choiceList.value?.let { answerList ->
            answerList.find { answer -> answer.id == answerId }?.apply {
                isChosen = isChosen.not()
            } ?: throw Exception("Not found answer")
        } ?: throw Exception("List of answers is null")

        ioScope.launch { answerDao.save(newAnswer) }
    }

    fun getAnswerSyncById(answerId: Int) = answerDao.getLiveDataById(answerId)
}