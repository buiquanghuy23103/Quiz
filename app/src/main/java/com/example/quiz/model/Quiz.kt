package com.example.quiz.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(
    tableName = "quizzes",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["categoryId"])]
)
data class Quiz(
    var text: String = "",
    var categoryId: Int = 0,
    var explanation: String? = null,
    var imageUrl: String = "https://www.vegetables.co.nz/assets/Uploads/vegetables.jpg",
    var optionA: String = "Canberra is the capital of Australia",
    var optionB: String = "Canberra is the capital of Australia",
    var optionC: String = "Canberra is the capital of Australia",
    var optionD: String = "Canberra is the capital of Australia",
    var answer: String = "A",

    @Exclude
    @PrimaryKey
    var id: String = ""
): BaseModel {
    override fun withId(id: String) {
        this.id = id
    }
}