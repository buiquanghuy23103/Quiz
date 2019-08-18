package com.example.quiz.framework

import androidx.lifecycle.ViewModel
import com.example.quiz.dagger.Injector

abstract class BaseViewModel : ViewModel(){
    private val database = Injector.get().appDatabase()
    val quizDao = database.quizDao
    val choiceDao = database.choiceDao
    val categoryDao = database.categoryDao

}