package com.example.quiz.data.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataPrepopulation(val appContext: Context) : RoomDatabase.Callback() {
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase = AppDatabase.getInstance(appContext)
            insertSampleData()
            appDatabase.isSampleDataInserted.postValue(true)
        }
    }

    private suspend fun insertSampleData() {
        appDatabase.withTransaction {
            appDatabase.quizDao.saveMany(SampleData.sampleQuizList)
            appDatabase.answerDao.save(SampleData.sampleAnswerList)
        }
    }
}