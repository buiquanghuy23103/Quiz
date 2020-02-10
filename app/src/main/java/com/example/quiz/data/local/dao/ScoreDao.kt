package com.example.quiz.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.quiz.model.Score
import io.reactivex.Single

@Dao
interface ScoreDao: BaseDao<Score> {

    @Query("SELECT * FROM score WHERE userId = :userId AND categoryId = :categoryId")
    fun find(userId: String, categoryId: String): Single<Score>

}