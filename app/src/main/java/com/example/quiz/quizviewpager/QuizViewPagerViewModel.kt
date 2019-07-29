package com.example.quiz.quizviewpager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import kotlinx.coroutines.launch

class QuizViewPagerViewModel: BaseViewModel() {
    private val _quizIdList = MutableLiveData<List<Int>>()
    val quizIdList: LiveData<List<Int>>
        get() = _quizIdList

    init {
        ioScope.launch { _quizIdList.postValue(quizDao.getIdList()) }
    }
}
