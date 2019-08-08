package com.example.quiz.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.sampleQuiz
import com.example.quiz.sampleQuizList
import com.google.common.truth.Truth.assertThat
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QuizDaoTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var quizDao: QuizDao
    private lateinit var sampleQuizIdList: List<Long>

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        quizDao = appDatabase.quizDao
        sampleQuizIdList = quizDao.saveList(sampleQuizList)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetQuiz() {
        val quizId = sampleQuizIdList[0].toInt()
        val quizFromDb = quizDao.getById(quizId)
        assertThat(quizFromDb.id).isEqualTo(sampleQuiz.id)
        assertThat(quizFromDb.text).isEqualTo(sampleQuiz.text)
    }

    @Test
    fun testSaveAndGetQuizList() {

        val quizListFromDb = quizDao.getAll()

        quizListFromDb.test()
            .assertHasValue()
            .assertValue(sampleQuizList)


    }
}