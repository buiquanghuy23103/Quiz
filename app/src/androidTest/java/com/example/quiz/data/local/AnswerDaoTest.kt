package com.example.quiz.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.AnswerDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.util.sampleAnswersOfSampleQuiz
import com.example.quiz.util.sampleQuiz
import com.google.common.truth.Truth.assertThat
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AnswerDaoTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var answerDao: AnswerDao
    private lateinit var quizDao: QuizDao
    private var sampleQuizId: Int = 0

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        answerDao = appDatabase.answerDao
        quizDao = appDatabase.quizDao
        sampleQuizId = quizDao.save(sampleQuiz).toInt()
        answerDao.saveList(sampleAnswersOfSampleQuiz)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetAnswerList() {

        val answerListFromDb = answerDao.getAnswersByQuizId(sampleQuizId)
        for (i in 0 until answerListFromDb.size - 1) {
            // The order of answers in database is not the same as that in sampleAnswersOfSampleQuiz
            val currentAnswerIdFromDb = answerListFromDb[i].id
            val currentAnswerFromSample =
                sampleAnswersOfSampleQuiz.find { answer -> answer.id == currentAnswerIdFromDb }

            assertThat(currentAnswerFromSample).isNotNull()
            assertThat(answerListFromDb[i].quizId).isEqualTo(currentAnswerFromSample?.quizId)
            assertThat(answerListFromDb[i].isTrue).isEqualTo(currentAnswerFromSample?.isTrue)
            assertThat(answerListFromDb[i].isChosen).isEqualTo(currentAnswerFromSample?.isChosen)
        }
    }

    @Test
    fun testGetAnswerAsLiveDataById() {
        val sampleAnswer = sampleAnswersOfSampleQuiz[0]
        val answerFromDbAsLiveData = answerDao.getLiveDataById(sampleAnswer.id)
        answerFromDbAsLiveData.test()
            .awaitValue()
            .assertHasValue()
            .assertValue(sampleAnswer)
    }

}