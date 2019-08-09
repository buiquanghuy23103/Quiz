package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideAppDatabase(appContext: Context) = AppDatabase.getInstance(appContext)
}