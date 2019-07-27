package com.example.quiz.injection

import com.example.quiz.MainApplication
import com.example.quiz.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideAppDatabase(): AppDatabase{
        val appContext = MainApplication.INSTANCE.applicationContext
        return AppDatabase.getInstance(appContext)
    }
}