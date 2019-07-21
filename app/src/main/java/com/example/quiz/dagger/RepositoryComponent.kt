package com.example.quiz.dagger

import com.example.quiz.database.DataRepository
import dagger.Component

@Component(modules = [AppModule::class])
interface RepositoryComponent {
    fun getRepository(): DataRepository
}