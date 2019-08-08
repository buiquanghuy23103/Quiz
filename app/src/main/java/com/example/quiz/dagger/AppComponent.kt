package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.data.local.AppDatabase
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun appContext(): Context
    fun appDatabase(): AppDatabase
}