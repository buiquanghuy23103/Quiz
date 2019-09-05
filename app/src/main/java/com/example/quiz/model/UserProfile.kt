package com.example.quiz.model

import com.google.firebase.auth.FirebaseUser

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
    }
}