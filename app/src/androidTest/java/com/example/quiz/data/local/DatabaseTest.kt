package com.example.quiz.data.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.quiz.data.local.dao.QuizDao
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class DatabaseTest {
    private lateinit var appDatabase: AppDatabase
    protected lateinit var quizDao: QuizDao

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
}