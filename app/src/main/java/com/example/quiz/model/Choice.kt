package com.example.quiz.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(
    tableName = "choices",
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["id"],
        childColumns = ["quizId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["quizId"])]
)
data class Choice(
    var quizId: Int = 0,
    var text: String = "Default text",
    var correct: Boolean = false,

    @Exclude
    @PrimaryKey
    var id: String = ""
): BaseModel{
    override fun withId(id: String) {
        this.id = id
    }
}