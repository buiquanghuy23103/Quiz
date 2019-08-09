package com.example.quiz.data.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quiz.SingletonHolder
import com.example.quiz.data.local.dao.ChoiceDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

@Database(entities = [Quiz::class, Choice::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val choiceDao: ChoiceDao
    var isSampleDataInserted = MutableLiveData<Boolean>()

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
            it,
            AppDatabase::class.java,
            DbScheme.DATABASE_NAME
        )
            .addCallback(DataPrepopulation(it))
            .fallbackToDestructiveMigration()
            .build()
    })
}