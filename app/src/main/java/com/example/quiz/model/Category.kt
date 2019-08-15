package com.example.quiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "categories")
data class Category(
    var text: String = "",
    var imageUrl: String = "",

    @PrimaryKey
    var id: Int = Random.nextInt()
)