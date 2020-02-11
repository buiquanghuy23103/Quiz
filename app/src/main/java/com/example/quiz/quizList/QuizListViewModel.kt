package com.example.quiz.quizList

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.combineAndCompute
import com.example.quiz.countDownInitial
import com.example.quiz.countDownInterval
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.data.local.dao.ScoreDao
import com.example.quiz.model.Score
import com.example.quiz.questionFinishSignal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class QuizListViewModel @Inject constructor(
    private val quizDao: QuizDao,
    private val categoryDao: CategoryDao,
    private val scoreDao: ScoreDao,
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private lateinit var categoryId: String
    private lateinit var categoryName: String
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

    fun quizIdList(): LiveData<List<String>> {
        val allQuizIdsLiveData = quizDao.getQuizIdListByCategory(categoryId)
        val allAnsweredQuizIdsLiveData = scoreDao.getAlreadyAnsweredQuizIds(categoryId)

        return allQuizIdsLiveData.combineAndCompute(allAnsweredQuizIdsLiveData)
        { allQuizIds, allAnsweredQuizIds ->
            allQuizIds.minus(allAnsweredQuizIds)
        }

    }


    fun category() = categoryDao.getById(categoryId)

    fun withCategoryId(categoryId: String) {
        this.categoryId = categoryId
    }

    fun withCategoryName(categoryName: String) {
        this.categoryName = categoryName
    }

    fun resetTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

    fun moveToNextQuestion(quizId: String, correctAnswer: String, userAnswer: String) {
        markQuestionAsFinished()
            .mergeWith(saveAnswer(quizId, correctAnswer, userAnswer))
            .subscribe()
            .addTo(compositeDisposable)

    }

    private fun markQuestionAsFinished(): Completable {
        return Completable.fromAction {
            Thread.sleep(1000)
            _timeLeft.postValue(questionFinishSignal)
        }
            .subscribeOn(Schedulers.io())
    }

    private fun getCurrentUserId() = firebaseAuth.currentUser!!.uid

    private fun saveAnswer(
        quizId: String,
        correctAnswer: String,
        userAnswer: String
    ) = Completable.create {emitter ->

        val newScore = Score(
            categoryId = this.categoryId,
            quizId = quizId,
            userAnswer = userAnswer,
            userId = getCurrentUserId(),
            rightAnswer = correctAnswer,
            isCorrect = (userAnswer == correctAnswer),
            categoryName = categoryName
        )

        firestore.collection(Score::class.java.simpleName)
            .add(newScore)
            .addOnSuccessListener {
                Timber.i("Saved user answer")
                emitter.onComplete()
            }
            .addOnFailureListener{error ->
                Timber.e("Cannot save answer: $error")
                emitter.onError(error)
            }
    }.subscribeOn(Schedulers.io())


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
