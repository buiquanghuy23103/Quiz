package com.example.quiz.dagger

import com.example.quiz.MainApplication

class Injector private constructor() {
    companion object {
        fun get(): AppComponent = MainApplication.get().component
    }
}