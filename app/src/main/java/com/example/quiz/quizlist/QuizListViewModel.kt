package com.example.quiz.quizlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    private val _quizList = MutableLiveData<List<Quiz>>()
    val quizList: LiveData<List<Quiz>>
        get() = _quizList

    init {
        ioScope.launch { loadQuizList() }
    }

    private val newQuiz = Quiz("New text")
    val newQuizId = newQuiz.id
    private val newAnswerList = listOf(
        Answer(newQuizId, "Answer 1", true),
        Answer(newQuizId, "Answer 2", false)
    )

    fun createAndSaveNewQuiz(){
        ioScope.launch { quizDao.save(newQuiz) }
    }

    fun createAndSaveNewAnswerList(){
        ioScope.launch { answerDao.saveList(newAnswerList) }
    }

    fun deleteQuiz(quiz: Quiz){
        ioScope.launch {
            quizDao.delete(quiz)
            loadQuizList()
        }
    }

    private suspend fun loadQuizList() {
        _quizList.postValue(quizDao.getAll())
    }
}