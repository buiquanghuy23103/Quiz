package com.example.quiz.profile

import androidx.lifecycle.ViewModel
import com.example.quiz.data.local.dao.CategoryDao
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth,
    categoryDao: CategoryDao
): ViewModel() {

    val userDetails = firebaseAuth.currentUser

    val achievements = categoryDao.getAchievements()


}