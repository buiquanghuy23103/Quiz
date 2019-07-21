package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideAppContext(): Context = MainApplication.INSTANCE.applicationContext
}