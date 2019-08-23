package com.example.quiz.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class FirebaseFetch<T : Any>(private val classType: Class<T>) {

    private val db = FirebaseDatabase.getInstance()
    private val dbReference = db.reference.child(classType.simpleName)
    private lateinit var valueEventListener: ValueEventListener

    val dataList: List<T>

    init {
        dataList = intiDataList()
    }

    private fun intiDataList(): List<T> {
        var value = listOf<T>()
        valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Timber.e("Error downloading ${classType.simpleName} from database: ${p0.toException()}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                value = p0.children.map { dataSnapshot ->
                    dataSnapshot.getValue(classType) ?: listOf<T>()
                } as List<T>
            }
        }
        dbReference.addValueEventListener(valueEventListener)

        return value
    }

    fun cleanUp() {
        dbReference.removeEventListener(valueEventListener)
    }
}