package com.example.quiz.framework

import androidx.lifecycle.ViewModel
import com.example.quiz.injection.DaggerRepositoryComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel(){
    val repository = DaggerRepositoryComponent.create().getRepository()

    private val backgroundJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}