package com.example.quiz.firebase

import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

const val ANONYMOUS = "anonymous"

object FirebaseAuthUtil {
    lateinit var listener: Listener
    private var username = ANONYMOUS
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    fun readUsername() = username

    fun signOut(context: Context) {
        AuthUI.getInstance().signOut(context)
    }

    fun setupAuthStateListener() {
        authStateListener = FirebaseAuth.AuthStateListener { currentAuth ->
            val user = currentAuth.currentUser
            if (user == null) {
                onSignOut()
            } else {
                user.displayName?.let { this.username = it }
                    ?: throw Exception("Username is null")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
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