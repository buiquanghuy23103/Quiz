package com.example.quiz.dagger

import android.content.Context
import androidx.room.Room
import com.example.quiz.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "quiz.db")
            .fallbackToDestructiveMigration()
            .build()
}