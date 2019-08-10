package com.example.quiz.quiz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quiz.sampleQuizList
import com.jraska.livedata.test
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QuizViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    val viewModel = QuizViewModel(sampleQuizList[0].id)

    @Before
    fun setUp() {
    }

    @Test
    fun getQuiz() {
        viewModel.quiz.test()
            .awaitValue()
            .assertHasValue()
    }

    @Test
    fun getChoiceList() {
    }

    @Test
    fun getAssessment() {
    }

    @Test
    fun toggleChoiceChosenById() {
    }

    @Test
    fun getChoiceById() {
    }
}