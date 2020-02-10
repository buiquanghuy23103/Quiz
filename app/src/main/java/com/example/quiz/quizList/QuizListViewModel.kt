package com.example.quiz.quizList

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.countDownInitial
import com.example.quiz.countDownInterval
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.questionFinishSignal
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuizListViewModel @Inject constructor(
    private val quizDao: QuizDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private lateinit var categoryId: String
    private val compositeDisposable = CompositeDisposable()

    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> = _timeLeft
    private val countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval){
        override fun onTick(millisLeft: Long) {
            _timeLeft.value = millisLeft
        }

        override fun onFinish() {
            _timeLeft.value = questionFinishSignal
        }
    }

    fun quizIdList() = quizDao.getQuizIdListByCategory(categoryId)
    fun category() = categoryDao.getById(categoryId)

    fun withCategoryId(categoryId: String) {
        this.categoryId = categoryId
    }

    fun resetTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

    fun moveToNextQuestion() {
        Completable.fromAction {
            Thread.sleep(1000)
            _timeLeft.postValue(questionFinishSignal)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
