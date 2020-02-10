package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.BaseDao
import com.example.quiz.model.BaseModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FirebaseFetch<T: BaseModel>(
    private val dao: BaseDao<T>,
    private val classType: Class<T>,
    private val firestore: FirebaseFirestore
) {
    fun downloadData(): Completable {
        return fetchData()
            .flatMapCompletable(this::saveToLocalDatabase)
    }

    private fun fetchData(): Single<List<T>> {
        return Single.create<List<T>> { emitter ->

            firestore.collection(classType.simpleName)
                .addSnapshotListener{ value, error ->

                    if (error != null) {
                        Timber.e(error)
                        return@addSnapshotListener
                    }

                    val data = value!!.toData()
                    emitter.onSuccess(data)

                }
        }.subscribeOn(Schedulers.io())
    }

    private fun QuerySnapshot.toData(): List<T> {

        Timber.i(this.documents.toString())

        return this.documents.map { document ->

            val newData = document?.toObject(classType)
            newData?.withId(document.id)
            return@map newData

        }.filterNotNull()
    }

    private fun saveToLocalDatabase(data: List<T>): Completable {
        return dao.saveList(data)
            .subscribeOn(Schedulers.io())
    }

}