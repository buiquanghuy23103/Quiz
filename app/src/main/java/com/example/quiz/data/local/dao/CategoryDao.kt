package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.data.local.DbScheme.CategoryTable
import com.example.quiz.model.Category

@Dao
interface CategoryDao {
    @Insert
    fun saveList(categoryList: List<Category>)

    @Query("SELECT * FROM ${CategoryTable.TABLE_NAME}")
    fun getAll(): LiveData<List<Category>>
}