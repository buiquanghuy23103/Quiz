package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.BaseDao
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class FirebaseFetch<T>(
    private val dao: BaseDao<T>,
    private val classType: Class<T>,
    private val defaultObject: T
) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var dbRef: DatabaseReference
    private lateinit var childEventListener: ChildEventListener

    fun downloadData() {
        saveData()
        listenToNewData()
    }

    private fun saveData() {
        fetchObservableData()
            .retry()
            .observeOn(Schedulers.io())
            .subscribe {
                dao.saveList(it)
            }.addTo(compositeDisposable)
    }

    private fun listenToNewData() {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.reference.child(classType.simpleName)
        dbRef.addChildEventListener(childEventListener)
    }

    private fun fetchObservableData(): Observable<List<T>> {
        return Observable.create { emitter ->
            val db = FirebaseFirestore.getInstance()
            db.collection(classType.simpleName)
                .get()
                .addOnSuccessListener {
                    emitter.onNext(it.toData())
                }
        }
    }

    private fun QuerySnapshot.toData(): List<T> {
        return this.documents.map { document ->
            document.toObject(classType) ?: defaultObject
        }
    }

    fun cleanUp() {
        compositeDisposable.dispose()
        dbRef.removeEventListener(childEventListener)
    }

}