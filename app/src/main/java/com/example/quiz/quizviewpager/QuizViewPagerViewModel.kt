package com.example.quiz.quizviewpager

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel(categoryId: Int) : BaseViewModel() {
    val quizIdList = quizDao.getQuizIdListByCategory(categoryId)
    val category = categoryDao.getById(categoryId)

    val countDownInitial: Long = 20000
    val countDownInterval: Long = 1000
    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> = _timeLeft
    private val countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval){
        override fun onTick(millisLeft: Long) {
            _timeLeft.value = millisLeft
        }

        override fun onFinish() {
            _timeLeft.value = 0
        }
    }

    fun resetTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

}
