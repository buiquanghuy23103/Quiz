package com.example.quiz.database

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quiz.model.Quiz

@Database(entities = [Quiz::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    var isSampleDataInserted = MutableLiveData<Boolean>()

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun from(context: Context): AppDatabase{
            synchronized(this){
                val appContext = context.applicationContext
                var instance = INSTANCE
                if (instance == null){
                    instance = buildDatabaseFrom(appContext)
                    INSTANCE = instance
                    // double-take
                    if (appContext.getDatabasePath(DbScheme.DATABASE_NAME).exists()){
                        INSTANCE!!.isSampleDataInserted.postValue(true)
                    }
                }
                return instance
            }
        }

        private fun buildDatabaseFrom(appContext: Context): AppDatabase{
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                DbScheme.DATABASE_NAME
            )
                .addCallback(SampleDataGenerator(appContext))
                .fallbackToDestructiveMigration()
                .build()
        }


    }
}