package com.example.quiz.profile

import androidx.lifecycle.MutableLiveData
import com.example.quiz.framework.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel: BaseViewModel() {

    private val auth = FirebaseAuth.getInstance()
    val userDetails = MutableLiveData<FirebaseUser>(auth.currentUser)

}