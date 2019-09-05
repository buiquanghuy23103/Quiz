package com.example.quiz.model

import com.example.quiz.alert
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

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

        fun getAllUsers(): List<UserProfile> {
            var userProfiles = listOf<UserProfile>()
            val db = FirebaseFirestore.getInstance()
            val userRef = db.collection("UserProfile")

            userRef.get()
                .addOnSuccessListener {
                    userProfiles = it.toUserProfile()
                }
                .addOnCanceledListener {
                    alert("Cannot download user profiles")
                }

            return userProfiles
        }

        private fun QuerySnapshot.toUserProfile(): List<UserProfile> {
            return this.documents.map { documentSnapshot ->
                documentSnapshot.toObject(UserProfile::class.java) ?: UserProfile()
            }
        }
    }
}