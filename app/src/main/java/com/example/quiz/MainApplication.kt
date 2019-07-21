package com.example.quiz

import android.app.Application
import timber.log.Timber

class MainApplication: Application() {
    companion object{
        lateinit var INSTANCE: MainApplication
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        INSTANCE = this
    }
}