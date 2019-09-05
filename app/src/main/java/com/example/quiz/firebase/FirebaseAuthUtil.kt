package com.example.quiz.firebase

import android.content.Context
import android.content.Intent
import com.example.quiz.model.UserProfile
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

const val ANONYMOUS = "anonymous"

object FirebaseAuthUtil {
    lateinit var listener: Listener
    var userProfile = UserProfile()
    private var username = ANONYMOUS
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private val compositeDisposable = CompositeDisposable()

    fun readUsername() = username

    fun signOut(context: Context) {
        AuthUI.getInstance().signOut(context)
    }

    fun setupAuthStateListener() {
        authStateListener = FirebaseAuth.AuthStateListener { currentAuth ->
            val firebaseUser = currentAuth.currentUser
            userProfile = UserProfile.from(firebaseUser)
            if (firebaseUser == null) {
                onSignOut()
            } else {
                firebaseUser.displayName?.let { this.username = it }
                    ?: throw Exception("Username is null")

                UserProfile.getAllUsers().subscribe {
                    val isNewUser = it.firstOrNull() == null
                    if (isNewUser) {
                        val db = FirebaseFirestore.getInstance()
                        val userRef = db.collection("UserProfile")
                        userRef.add(userProfile)
                    }
                }.addTo(compositeDisposable)

            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun cleanUp() {
        firebaseAuth.removeAuthStateListener(authStateListener)
        compositeDisposable.dispose()
    }

    private fun onSignOut() {
        username = ANONYMOUS
        listener.startAuthUI(getAuthIntent())
    }

    private fun getAuthIntent(): Intent {
        val authProviders = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(false)
            .setAvailableProviders(authProviders)
            .build()
    }

    interface Listener : FirebaseUtil.Listener {
        fun startAuthUI(authIntent: Intent)
    }
}