package com.example.quiz.data.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.AnswerDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.sampleAnswersOfSampleQuiz
import com.example.quiz.sampleQuiz
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var quizDao: QuizDao
    private lateinit var answerDao: AnswerDao

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        quizDao = appDatabase.quizDao
        answerDao = appDatabase.answerDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetQuiz() {
        quizDao.save(sampleQuiz)
        val quiz = quizDao.getById(sampleQuiz.id)
        assertThat(quiz).isEqualTo(sampleQuiz)
    }

    @Test
    fun testSaveAndGetAnswer() {
        answerDao.saveList(sampleAnswersOfSampleQuiz)
        val answerList = answerDao.getAnswersByQuizId(sampleQuiz.id)
        assertThat(answerList).isEqualTo(sampleAnswersOfSampleQuiz)
    }
}