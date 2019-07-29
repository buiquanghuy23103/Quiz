package com.example.quiz.framework

import androidx.lifecycle.ViewModel
import com.example.quiz.MainApplication
import com.example.quiz.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(){
    private val appContext = MainApplication.INSTANCE.applicationContext
    private val database = AppDatabase.getInstance(appContext)
    val quizDao = database.quizDao
    val answerDao = database.answerDao

    private val backgroundJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}