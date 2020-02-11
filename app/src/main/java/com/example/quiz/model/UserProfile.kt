package com.example.quiz.model

import com.example.quiz.utils.alert
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

data class UserProfile(
    var uid: String = "a123",
    var name: String = "unknown",
    var email: String = "email@gmail.com",
    var photoUrl: String = ""
) {
    companion object {
        fun from(user: FirebaseUser?): UserProfile =
            user?.let {
                UserProfile(
                    uid = user.uid,
                    name = user.displayName ?: "anonymous",
                    email = user.email ?: "huythdnbkltv@gmail.com",
                    photoUrl = user.photoUrl.toString()
                )
            } ?: UserProfile()

        fun getAllUsers(): Observable<List<UserProfile>> {
            val observable = Observable.create<List<UserProfile>> { emitter ->
                val db = FirebaseFirestore.getInstance()
                val userRef = db.collection("UserProfile")

                userRef.get()
                    .addOnSuccessListener { querySnapshot ->
                        with(querySnapshot.toUserProfile()) {
                            if (!this.isNullOrEmpty()) {
                                emitter.onNext(this)
                                emitter.onComplete()
                            }
                        }
                    }
                    .addOnCanceledListener {
                        alert("Cannot download user profiles")
                        emitter.onNext(listOf())
                    }

            }

            return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .share()
        }

        private fun QuerySnapshot.toUserProfile(): List<UserProfile> {
            return this.documents.map { documentSnapshot ->
                documentSnapshot.toObject(UserProfile::class.java) ?: UserProfile()
            }
        }
    }
}