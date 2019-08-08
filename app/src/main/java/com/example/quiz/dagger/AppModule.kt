package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val appContext: Context) {
    @Provides
    fun provideAppContext(): Context = appContext

    @Provides
    fun provideAppDatabase() = AppDatabase.getInstance(appContext)
}