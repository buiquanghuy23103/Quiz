package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.MainApplication
import com.example.quiz.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideAppDatabase(): AppDatabase{
        val appContext = MainApplication.INSTANCE.applicationContext
        return AppDatabase.getInstance(appContext)
    }
}