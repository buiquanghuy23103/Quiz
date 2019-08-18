package com.example.quiz.firebase

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

const val ANONYMOUS = "anonymous"

class FirebaseAuthUtil {
    lateinit var listener: Listener
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    fun setupAuthStateListener(onSignIn: (String) -> Unit, onSignOut: (Intent) -> Unit) {
        authStateListener = FirebaseAuth.AuthStateListener { currentAuth ->
            currentAuth.currentUser?.let {
                val username = it.displayName ?: ANONYMOUS
                onSignIn(username)
            } ?: onSignOut(getAuthIntent())
        }
        firebaseAuth.addAuthStateListener(authStateListener)
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

    interface Listener {
        fun startAuthUI(authIntent: Intent)
    }
}