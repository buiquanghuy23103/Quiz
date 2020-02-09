package com.example.quiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.ChoiceDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.data.local.dao.ScoreDao
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import com.example.quiz.model.Score

// The implementation of this class is based on Android Sunflower
// https://github.com/googlesamples/android-sunflower
@Database(
    entities = [Category::class, Quiz::class, Choice::class, Score::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val choiceDao: ChoiceDao
    abstract val categoryDao: CategoryDao
    abstract val scoreDao: ScoreDao
}