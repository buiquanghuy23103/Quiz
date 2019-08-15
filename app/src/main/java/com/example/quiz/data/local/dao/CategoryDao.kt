package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.model.Category

@Dao
interface CategoryDao {
    @Insert
    fun saveList(categoryList: List<Category>)

    @Query("SELECT * FROM categories")
    fun getAll(): LiveData<List<Category>>
}