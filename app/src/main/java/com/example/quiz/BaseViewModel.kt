package com.example.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.quiz.dagger.DaggerRepositoryComponent
import com.example.quiz.dagger.RepositoryComponent
import com.example.quiz.database.DataRepository
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    val repository = DaggerRepositoryComponent.create().getRepository()
}