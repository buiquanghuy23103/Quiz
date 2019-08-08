package com.example.quiz.data.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.sampleQuiz
import com.example.quiz.sampleQuizList
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QuizDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var quizDao: QuizDao

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        quizDao = appDatabase.quizDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun testSaveAndGetQuiz() {
        val quizId = quizDao.save(sampleQuiz).toInt()
        val quizFromDb = quizDao.getById(quizId)
        assertThat(quizFromDb.id).isEqualTo(sampleQuiz.id)
        assertThat(quizFromDb.text).isEqualTo(sampleQuiz.text)
    }

    @Test
    fun testSaveAndGetQuizList() {
        val quizIdList = quizDao.saveList(sampleQuizList)
        val quizListFromDb = quizDao.getAll()
        for (i in 0 until quizIdList.size - 1) {
            assertThat(quizListFromDb[i].id).isEqualTo(quizIdList[i].toInt())
        }
    }
}