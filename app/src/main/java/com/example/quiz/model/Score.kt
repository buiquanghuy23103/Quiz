package com.example.quiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

@IgnoreExtraProperties
@Entity
data class Score(
    var userId: String = "",
    var categoryId: String = "",
    var quizId: String = "",
    var rightAnswer: String = "",
    var userAnswer: String = "",
    var isCorrect: Boolean = false,
    var categoryName: String = "",

    @ServerTimestamp
    var createdAt: Date = Date(),

    @Exclude @set:Exclude @get:Exclude
    @PrimaryKey
    var id: String = ""
): BaseModel {
    override fun withId(id: String) {
        this.id = id
    }
}