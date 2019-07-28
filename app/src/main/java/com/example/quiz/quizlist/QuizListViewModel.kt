package com.example.quiz.quizlist

import com.example.quiz.framework.BaseViewModel
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz
import kotlinx.coroutines.launch

class QuizListViewModel : BaseViewModel() {
    val quizList = repository.getAllQuizzes()

    private val newQuiz = Quiz("New text")
    val newQuizId = newQuiz.id
    private val newAnswerList = listOf(
        Answer(newQuizId, "Answer 1", true),
        Answer(newQuizId, "Answer 2", false)
    )

    fun createAndSaveNewQuiz(){
        ioScope.launch { repository.saveQuiz(newQuiz) }
    }

    fun createAndSaveNewAnswerList(){
        ioScope.launch { repository.saveAnswerList(newAnswerList) }
    }

    fun deleteQuiz(quiz: Quiz){
        ioScope.launch { repository.deleteQuiz(quiz) }
    }
}