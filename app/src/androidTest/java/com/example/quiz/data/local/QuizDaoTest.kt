package com.example.quiz.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quiz.util.sampleQuiz
import com.example.quiz.util.sampleQuizList
import com.jraska.livedata.test
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuizDaoTest : DatabaseTest() {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        quizDao.saveList(sampleQuizList)
    }

    @Test
    fun testSaveAndGetQuiz() {
        val quizId = sampleQuiz.id
        val quizFromDb = quizDao.getById(quizId)

        quizFromDb.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(sampleQuiz)
    }

    @Test
    fun testSaveAndGetQuizList() {

        val quizListFromDb = quizDao.getAll()

        quizListFromDb.test()
            .assertHasValue()
            .assertValue(sampleQuizList)
    }
}