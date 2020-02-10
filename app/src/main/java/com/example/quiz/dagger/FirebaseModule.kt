package com.example.quiz.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

}