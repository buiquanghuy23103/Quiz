package com.example.quiz.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.ChoiceDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.util.sampleAnswersOfSampleQuiz
import com.example.quiz.util.sampleQuiz
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ChoiceDaoTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var choiceDao: ChoiceDao
    private lateinit var quizDao: QuizDao
    private var sampleQuizId: Int = 0

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        choiceDao = appDatabase.choiceDao
        quizDao = appDatabase.quizDao
        sampleQuizId = quizDao.save(sampleQuiz).toInt()
        choiceDao.saveList(sampleAnswersOfSampleQuiz)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetAnswerList() {

        choiceDao.getChoiceByQuizId(sampleQuizId).test()
            .awaitValue()
            .assertHasValue()
            .assertValue(sampleAnswersOfSampleQuiz)

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