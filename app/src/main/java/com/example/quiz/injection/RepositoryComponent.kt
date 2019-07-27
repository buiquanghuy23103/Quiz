package com.example.quiz.injection

import com.example.quiz.data.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface RepositoryComponent {
    fun getRepository(): Repository
}