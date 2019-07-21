package com.example.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quiz.dagger.RepositoryComponent
import com.example.quiz.database.DataRepository
import javax.inject.Inject

abstract class BaseViewModel(app: Application) : AndroidViewModel(app){
    @Inject lateinit var repository: DataRepository

    init {

    }
}