package com.example.quiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "categories")
data class Category(
    var text: String = "",
    var imageUrl: String = "",

    @Exclude
    @PrimaryKey
    var id: String = ""
): BaseModel {

    override fun withId(id: String) {
        this.id = id
    }
}