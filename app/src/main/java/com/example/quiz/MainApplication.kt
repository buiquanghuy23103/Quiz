package com.example.quiz

import androidx.multidex.MultiDexApplication
import com.example.quiz.dagger.AppComponent
import com.example.quiz.dagger.DaggerAppComponent
import timber.log.Timber

class MainApplication: MultiDexApplication() {
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
        component = DaggerAppComponent.factory().create(this)
    }
}