package com.example.quiz.quizviewpager

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel

class QuizViewPagerViewModel(categoryId: Int) : BaseViewModel() {
    val quizIdList = quizDao.getQuizIdListByCategory(categoryId)
    val category = categoryDao.getById(categoryId)

    private val countDownInitial: Long = 20000
    private val countDownInterval: Long = 1000
    private lateinit var countDownTimer: CountDownTimer
    private val _timeLeft = MutableLiveData<Long>(countDownInitial)
    val timeLeft :LiveData<Long> = _timeLeft

    fun startTimer() {

        countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinnished: Long) {
                _timeLeft.value = millisUntilFinnished / 1000
            }
        }.start()

    }

}
