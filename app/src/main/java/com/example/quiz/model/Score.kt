package com.example.quiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity
data class Score(
    var userId: String = "",
    var categoryId: String = "",
    var score: Int = 0,

    @Exclude @set:Exclude @get:Exclude
    @PrimaryKey
    var id: String = ""
): BaseModel {
    override fun withId(id: String) {
        this.id = id
    }
}