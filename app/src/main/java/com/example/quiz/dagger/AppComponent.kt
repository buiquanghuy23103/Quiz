package com.example.quiz.dagger

import android.content.Context
import com.example.quiz.data.local.AppDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        FirebaseModule::class
    ]
)
@Singleton
interface AppComponent {
    fun appContext(): Context
    fun appDatabase(): AppDatabase
    fun firestore(): FirebaseFirestore

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }
}