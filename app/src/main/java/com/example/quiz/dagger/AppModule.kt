package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.MainApplication
import com.example.quiz.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(): Context = MainApplication.INSTANCE.applicationContext

    @Provides
    fun provideAppDatabase(appContext: Context): AppDatabase
            = AppDatabase.getInstance(appContext)
}