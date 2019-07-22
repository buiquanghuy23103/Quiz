package com.example.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.quiz.dagger.DaggerRepositoryComponent
import com.example.quiz.dagger.RepositoryComponent
import com.example.quiz.database.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    val repository = DaggerRepositoryComponent.create().getRepository()

    private val backgroundJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    override fun onCleared() {
        super.onCleared()
        backgroundJob.cancel()
    }
}