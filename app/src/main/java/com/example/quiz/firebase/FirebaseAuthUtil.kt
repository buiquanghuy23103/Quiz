package com.example.quiz.firebase

import android.content.Context
import android.content.Intent
import com.example.quiz.model.UserProfile
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

object FirebaseAuthUtil {
    lateinit var listener: Listener
    var userProfileSubject = BehaviorSubject.createDefault(UserProfile())
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private val compositeDisposable = CompositeDisposable()

    fun signOut(context: Context) {
        AuthUI.getInstance().signOut(context)
    }

    fun setupAuthStateListener() {
        authStateListener = FirebaseAuth.AuthStateListener { currentAuth ->
            val firebaseUser = currentAuth.currentUser
            updateCurrentUserProfile(firebaseUser)
            if (firebaseUser == null) {
                onSignOut()
            } else {
                uploadNewUser()
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    private fun updateCurrentUserProfile(firebaseUser: FirebaseUser?) {
        val userProfile = UserProfile.from(firebaseUser)
        userProfileSubject.onNext(userProfile)
    }

    private fun uploadNewUser() {

    }

    fun cleanUp() {
        firebaseAuth.removeAuthStateListener(authStateListener)
        compositeDisposable.dispose()
    }

    private fun onSignOut() {
        userProfileSubject.onNext(UserProfile())
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