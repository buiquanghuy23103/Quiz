package com.example.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quiz.data.local.DbScheme.CategoryTable
import kotlin.random.Random

@Entity(tableName = CategoryTable.TABLE_NAME)
data class Category(
    @ColumnInfo(name = CategoryTable.Cols.TEXT)
    var text: String = "",

    @ColumnInfo(name = CategoryTable.Cols.IMAGE_URL)
    var imageUrl: String = "",

    @PrimaryKey
    @ColumnInfo(name = CategoryTable.Cols.ID)
    var id: Int = Random.nextInt()
)