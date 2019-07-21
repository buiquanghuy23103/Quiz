package com.example.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quiz.database.DataRepository

abstract class BaseViewModel(app: Application) : AndroidViewModel(app){
    protected val repository = DataRepository(app)
}