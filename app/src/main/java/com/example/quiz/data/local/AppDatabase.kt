package com.example.quiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.data.local.dao.ScoreDao
import com.example.quiz.model.Category
import com.example.quiz.model.Quiz
import com.example.quiz.model.Score

@Database(
    entities = [Category::class, Quiz::class, Score::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val categoryDao: CategoryDao
    abstract val scoreDao: ScoreDao
}