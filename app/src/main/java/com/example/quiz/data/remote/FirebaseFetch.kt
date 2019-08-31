package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.BaseDao
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FirebaseFetch<T>(
    private val dao: BaseDao<T>,
    private val classType: Class<T>,
    private val defaultObject: T
) {
    fun downloadData(): Completable {
        return fetchData().saveToLocalDatabase()
    }

    private fun fetchData(): Observable<List<T>> {
        return Observable.create { emitter ->
            val db = FirebaseFirestore.getInstance()
            db.collection(classType.simpleName)
                .get()
                .addOnSuccessListener {
                    emitter.onNext(it.toData())
                    emitter.onComplete()
                }
        }
    }

    private fun QuerySnapshot.toData(): List<T> {
        return this.documents.map { document ->
            document.toObject(classType) ?: defaultObject
        }
    }

    private fun Observable<List<T>>.saveToLocalDatabase(): Completable {
        return this.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMapCompletable {
                dao.saveList(it)
            }
            .andThen(Completable.complete())
    }

}