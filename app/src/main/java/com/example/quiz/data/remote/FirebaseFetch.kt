package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.BaseDao
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FirebaseFetch<T>(private val dao: BaseDao<T>, private val classType: Class<T>) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var dbRef: DatabaseReference
    private lateinit var childEventListener: ChildEventListener

    fun downloadData() {
        saveData()
        listenToNewData()
    }

    private fun saveData() {
        fetchObservableData()
            .observeOn(Schedulers.io())
            .subscribe {
                dao.save(it)
            }.addTo(compositeDisposable)
    }

    private fun listenToNewData() {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.reference.child(classType.simpleName)
        dbRef.addChildEventListener(childEventListener)
    }

    private fun fetchObservableData(): Observable<T> {
        return Observable.create<T> { emitter ->

            childEventListener = object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Timber.i("Error downloading data")
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    Timber.i("current child is moved")
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    p0.getValue(classType)?.let {
                        emitter.onNext(it)
                    }

                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    p0.getValue(classType)?.let {
                        emitter.onNext(it)
                    }
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    Timber.i("child ${p0.key} is removed")
                }
            }
        }
    }

    fun cleanUp() {
        compositeDisposable.dispose()
        dbRef.removeEventListener(childEventListener)
    }

}