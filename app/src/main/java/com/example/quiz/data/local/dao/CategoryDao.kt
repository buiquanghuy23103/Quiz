package com.example.quiz.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.quiz.model.Achievement
import com.example.quiz.model.Category
import io.reactivex.Observable

@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM categories")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM categories")
    fun getAllAsObservable(): Observable<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getById(categoryId: String): LiveData<Category>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getAchievements(): LiveData<List<Achievement>>

}