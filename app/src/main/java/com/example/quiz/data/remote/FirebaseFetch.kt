package com.example.quiz.data.remote

import com.example.quiz.dagger.Injector
import com.example.quiz.data.local.dao.BaseDao
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FirebaseFetch<T>(
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
            // Source can be CACHE, SERVER, or DEFAULT.
            val source = Source.CACHE

            // Get the document, forcing the SDK to use the offline cache
            db.collection(classType.simpleName)
                .get(source)
                .addOnSuccessListener {
                    emitter.onSuccess(it.toData())
                }
        }.subscribeOn(Schedulers.io())
    }

    private fun QuerySnapshot.toData(): List<T> {
        Timber.i(this.documents.toString())
        return this.documents.map { document ->
            document.toObject(classType) ?: defaultObject
        }
    }

    private fun saveToLocalDatabase(data: List<T>): Completable {
        return dao.saveList(data)
            .subscribeOn(Schedulers.io())
    }

}