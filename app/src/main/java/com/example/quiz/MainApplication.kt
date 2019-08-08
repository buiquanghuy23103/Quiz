package com.example.quiz

import android.app.Application
import com.example.quiz.dagger.AppComponent
import com.example.quiz.dagger.AppModule
import com.example.quiz.dagger.DaggerAppComponent
import timber.log.Timber

class MainApplication: Application() {
    lateinit var component: AppComponent
    companion object{
        private var INSTANCE: MainApplication? = null

        @JvmStatic
        fun get(): MainApplication = INSTANCE!!
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}