package com.example.quiz.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable


// The implementation of this interface is based on the following article:
// https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
// Give Florina credits for her awesome article!
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(objectList: List<T>): Completable// return list of objects' id for testing

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(obj: T): Completable // return object's id for testing
}