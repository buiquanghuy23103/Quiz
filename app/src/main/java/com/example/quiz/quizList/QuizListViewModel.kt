package com.example.quiz.quizList

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.countDownInitial
import com.example.quiz.countDownInterval
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.data.local.dao.ScoreDao
import com.example.quiz.model.Score
import com.example.quiz.questionFinishSignal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
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


    fun markAsCorrectAnswer() {

        scoreDao.find(getCurrentUserId(), categoryId)
            .subscribeOn(Schedulers.io())
            .subscribeBy (

                onSuccess = {score ->
                    incrementScore(score.id)
                        .subscribe()
                        .addTo(compositeDisposable)
                },

                onError = {
                    addNewScoreToFirestore()
                        .flatMapCompletable(this::storeNewScoreLocal)
                        .subscribe()
                        .addTo(compositeDisposable)
                }
            )
            .addTo(compositeDisposable)

    }

    private fun getCurrentUserId() = firebaseAuth.currentUser!!.uid

    private fun addNewScoreToFirestore() = Single.create<Score> { emitter ->
        val newScore = Score(getCurrentUserId(), categoryId)
        firestore.collection(Score::class.java.simpleName)
            .add(newScore)
            .addOnSuccessListener { docRef ->
                Timber.i("Added new score on Firebase cloud, score.id=${docRef.id}")
                newScore.withId(docRef.id)
                emitter.onSuccess(newScore)
            }
            .addOnFailureListener{ error ->
                Timber.e("Cannot add new score on Firebase cloud")
                emitter.onError(error)
            }
    }.subscribeOn(Schedulers.io())

    private fun storeNewScoreLocal(score: Score) = scoreDao.save(score)
        .subscribeOn(Schedulers.io())

    private fun incrementScore(scoreId: String) = Completable.create { emitter ->
        firestore.collection(Score::class.java.simpleName)
            .document(scoreId)
            .update("score", FieldValue.increment(1))
            .addOnSuccessListener {
                Timber.i("Incremented score on Firebase cloud, score.id=$scoreId")
                emitter.onComplete()
            }
            .addOnFailureListener{ error ->
                Timber.e("Cannot increment score on Firebase cloud, score.id=$scoreId")
                emitter.onError(error)
            }
    }.subscribeOn(Schedulers.io())

    fun markAsIncorrectAnswer() {
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
