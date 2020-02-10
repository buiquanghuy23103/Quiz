package com.example.quiz.quizviewpager

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.countDownFinnishSignal
import com.example.quiz.countDownInitial
import com.example.quiz.countDownInterval
import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel(categoryId: String) : BaseViewModel() {
    val quizIdList = quizDao.getQuizIdListByCategory(categoryId)
    val category = categoryDao.getById(categoryId)

    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> = _timeLeft
    private val countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval){
        override fun onTick(millisLeft: Long) {
            _timeLeft.value = millisLeft
        }

        override fun onFinish() {
            _timeLeft.value = countDownFinnishSignal
        }
    }

    fun resetTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

}
