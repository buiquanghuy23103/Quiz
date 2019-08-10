package com.example.quiz.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quiz.util.sampleAnswersOfSampleQuiz
import com.example.quiz.util.sampleQuiz
import com.jraska.livedata.test
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChoiceDaoTest : DatabaseTest() {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private var sampleQuizId: Int = 0

    @Before
    fun init() {
        sampleQuizId = quizDao.save(sampleQuiz).toInt()
        choiceDao.saveList(sampleAnswersOfSampleQuiz)
    }

    @Test
    fun testSaveAndGetAnswerList() {

        choiceDao.getChoicesByQuizId(sampleQuizId).test()
            .awaitValue()
            .assertHasValue()
            .assertValue { choiceList ->
                sampleAnswersOfSampleQuiz == choiceList.sortedBy { choice -> choice.text }
            }

    }

    @Test
    fun testGetAnswerAsLiveDataById() {
        val sampleAnswer = sampleAnswersOfSampleQuiz[0]
        val answerFromDbAsLiveData = choiceDao.getById(sampleAnswer.id)
        answerFromDbAsLiveData.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(sampleAnswer)
    }

}