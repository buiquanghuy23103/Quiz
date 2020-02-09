package com.example.quiz.model

import com.google.firebase.database.Exclude

@Suppress("UNCHECKED_CAST")
open class BaseModel {

    @Exclude
    var uid: String = ""

    fun <T : BaseModel> withId(uid: String): T {
        this.uid = uid
        return this as T
    }

}