package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.model.Category

@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM categories")
    fun getAll(): LiveData<List<Category>>
}