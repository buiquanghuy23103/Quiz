package com.example.quiz.firebase

import android.content.Context
import android.content.Intent
import com.example.quiz.dagger.Injector
import com.example.quiz.model.UserProfile
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

object FirebaseAuthUtil {
    lateinit var listener: Listener
    var userProfile = UserProfile()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private val compositeDisposable = CompositeDisposable()

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
                uploadNewUser()
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    private fun uploadNewUser() {
        UserProfile.getAllUsers().subscribe { firebaseUserList ->
            val findCurrentUser = firebaseUserList.firstOrNull {
                it.uid == userProfile.uid
            }
            val isNewUser = findCurrentUser == null
            if (isNewUser) {
                val db = Injector.get().firestore()
                val userRef = db.collection("UserProfile")
                userRef.add(userProfile)
            }
        }.addTo(compositeDisposable)
    }

    fun cleanUp() {
        firebaseAuth.removeAuthStateListener(authStateListener)
        compositeDisposable.dispose()
    }

    private fun onSignOut() {
        userProfile = UserProfile()
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