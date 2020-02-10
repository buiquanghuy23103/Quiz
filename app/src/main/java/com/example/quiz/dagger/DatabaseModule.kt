package com.example.quiz.dagger

import android.content.Context
import androidx.room.Room
import com.example.quiz.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "quiz.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao

    @Provides
    fun provideQuizDao(db: AppDatabase) = db.quizDao

    @Provides
    fun provideScoreDao(db: AppDatabase) = db.scoreDao

}