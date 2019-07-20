package com.example.quiz.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SampleDataGenerator(val appContext: Context): RoomDatabase.Callback(){
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase = AppDatabase.from(appContext)
            insertSampleData()
            appDatabase.isSampleDataInserted.postValue(true)
        }
    }

    private fun insertSampleData() {
        appDatabase.runInTransaction {
            appDatabase.quizDao.insertAll(DataGenerator.sampleQuizList)
        }
    }
}