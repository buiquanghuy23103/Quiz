package com.example.quiz.dagger

import com.example.quiz.database.DataRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface RepositoryComponent {
    fun getRepository(): DataRepository
}