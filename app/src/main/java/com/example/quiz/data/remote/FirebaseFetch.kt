package com.example.quiz.data.remote

import com.example.quiz.dagger.Injector
import com.example.quiz.data.local.dao.BaseDao
import com.example.quiz.model.BaseModel
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FirebaseFetch<T: BaseModel>(
    private val dao: BaseDao<T>,
    private val classType: Class<T>,
    private val defaultObject: T
) {
    fun downloadData(): Completable {
        return fetchData()
            .flatMapCompletable(this::saveToLocalDatabase)
    }

    private fun fetchData(): Single<List<T>> {
        return Single.create<List<T>> { emitter ->
            val db = Injector.get().firestore()


            db.collection(classType.simpleName)
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
            document.toObject(classType)?.withId(document.id) ?: defaultObject
        }
    }

    private fun saveToLocalDatabase(data: List<T>): Completable {
        return dao.saveList(data)
            .subscribeOn(Schedulers.io())
    }

}