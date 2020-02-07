package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.model.Category
import io.reactivex.Observable

@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM categories")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM categories")
    fun getAllAsObservable(): Observable<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getById(categoryId: Int): LiveData<Category>
}