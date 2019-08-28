package com.example.quiz.data.remote

import com.example.quiz.data.local.dao.BaseDao
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FirebaseFetch<T : Any>(private val classType: Class<T>) {

    fun downloadData(dao: BaseDao<T>): Completable {
        val downloadedData = Observable.create<List<T>> { emitter ->
            val db = FirebaseDatabase.getInstance()
            val dbRef = db.reference.child(classType.simpleName)

            val eventListener = object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Timber.e("Error downloading ${classType.simpleName} from database: ${p0.message}")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val dataList = p0.children.map { dataSnapshot ->
                        dataSnapshot.getValue(classType)
                    } as List<T>
                    emitter.onNext(dataList)
                }
            }

            dbRef.addValueEventListener(eventListener)
        }

        return downloadedData
            .doOnNext { Timber.i("zz onNext on thread: ${Thread.currentThread().name} with data = $it") }
            .filter { !it.isNullOrEmpty() }
            .observeOn(Schedulers.io())
            .flatMapCompletable {
                Timber.i("zz saving data on thread: ${Thread.currentThread().name}")
                dao.saveList(it)
            }
    }
}