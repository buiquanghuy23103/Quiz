package com.example.quiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quiz.model.Quiz
import java.util.concurrent.Executors

@Database(entities = [Quiz::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                return INSTANCE?.let { INSTANCE } ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase{
            val callback = DataCallback(context)
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DbScheme.DATABASE_NAME
            )
                .addCallback(callback)
                .fallbackToDestructiveMigration()
                .build()
        }

        class DataCallback(val context: Context): Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    val appDatabase = AppDatabase.getInstance(context)
                    appDatabase.runInTransaction {
                        appDatabase.quizDao.insertAllQuizzes(DataGenerator.quizzes)
                    }
                }
            }
        }
    }
}