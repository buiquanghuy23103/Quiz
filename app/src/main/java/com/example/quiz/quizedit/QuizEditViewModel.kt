package com.example.quiz.quizedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizEditViewModel(val quizId: Int) : BaseViewModel() {
    var finalQuizForSaving = Quiz("No change")
    var finalAnswerListForSaving = listOf<Answer>()

    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz
    private val _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>>
        get() = _answerList

    init {
        ioScope.launch {
            _quiz.postValue(quizDao.getById(quizId))
            _answerList.postValue(answerDao.getAnswersByQuizId(quizId))
        }
    }

    fun deleteAnswer(answer: Answer) {
        (finalAnswerListForSaving as ArrayList<Answer>).remove(answer)
        _answerList.value = finalAnswerListForSaving
    }

    fun saveQuiz(){
        ioScope.launch { quizDao.save(finalQuizForSaving) }
    }

    fun saveAnswerList(){
        ioScope.launch { answerDao.saveList(finalAnswerListForSaving) }
    }
}
