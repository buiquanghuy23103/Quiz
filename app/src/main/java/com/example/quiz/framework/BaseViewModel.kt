package com.example.quiz.framework

import androidx.lifecycle.ViewModel
import com.example.quiz.dagger.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(){
    private val database = Injector.get().appDatabase()
    val quizDao = database.quizDao
    val answerDao = database.choiceDao

    private val backgroundJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}