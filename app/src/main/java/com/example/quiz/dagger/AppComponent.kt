package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.data.local.AppDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {
    fun appContext(): Context
    fun appDatabase(): AppDatabase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }
}