package com.example.quiz.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth
): ViewModel() {

    val userDetails = MutableLiveData<FirebaseUser>(firebaseAuth.currentUser)

}