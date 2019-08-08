package com.example.quiz.data.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.AnswerDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.getValueBlocking
import com.example.quiz.sampleAnswer
import com.example.quiz.sampleAnswersOfSampleQuiz
import com.example.quiz.sampleQuiz
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AnswerDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var answerDao: AnswerDao
    private lateinit var quizDao: QuizDao

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        answerDao = appDatabase.answerDao
        quizDao = appDatabase.quizDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetAnswerList() {
        val quizId = quizDao.save(sampleQuiz).toInt()
        answerDao.saveList(sampleAnswersOfSampleQuiz)
        val answerListFromDb = answerDao.getAnswersByQuizId(quizId)
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
        quizDao.save(sampleQuiz)
        val answerId = answerDao.save(sampleAnswer).toInt()
        val answerFromDbAsLiveData = answerDao.getLiveDataById(answerId)
        val answerFromDb = answerFromDbAsLiveData.getValueBlocking()
        assertThat(answerFromDb?.quizId).isEqualTo(sampleAnswer.id)
        assertThat(answerFromDb?.text).isEqualTo(sampleAnswer.text)
        assertThat(answerFromDb?.isTrue).isEqualTo(sampleAnswer.isTrue)
        assertThat(answerFromDb?.isChosen).isEqualTo(sampleAnswer.isChosen)
    }

}