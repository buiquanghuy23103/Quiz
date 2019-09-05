package com.example.quiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quiz.data.local.dao.CategoryDao
import com.example.quiz.data.local.dao.ChoiceDao
import com.example.quiz.data.local.dao.QuizDao
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz

// The implementation of this class is based on Android Sunflower
// https://github.com/googlesamples/android-sunflower
// Thanks Sunflower team for a great work! Keep going!
// PS: Excuse me for not say thanks in public and not knowing the userUid of the team
@Database(
    entities = [Category::class, Quiz::class, Choice::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val choiceDao: ChoiceDao
    abstract val categoryDao: CategoryDao
}