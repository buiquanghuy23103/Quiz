package com.example.quiz.database

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quiz.SingletonHolder
import com.example.quiz.model.Quiz

@Database(entities = [Quiz::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    var isSampleDataInserted = MutableLiveData<Boolean>()

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
            it,
            AppDatabase::class.java,
            DbScheme.DATABASE_NAME
        )
            .addCallback(SampleDataGenerator(it))
            .fallbackToDestructiveMigration()
            .build()
    })
}