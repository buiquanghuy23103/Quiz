package com.example.quiz.data.local

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quiz.GsonUtil
import timber.log.Timber

// The implementation of this class is based on Android Sunflower
// https://github.com/googlesamples/android-sunflower
// Thanks Sunflower team for a great work! Keep going!
// PS: Excuse me for not say thanks in public and not knowing the name of the team
class SeedDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return generateData()
    }

    private fun generateData(): Result {
        try {
            return Result.success()
        } catch (ex: Exception) {
            Timber.e("Error seeding database: $ex")
            return Result.failure()
        }
    }

    @Throws(Exception::class)
    private fun insertSampleDataToDatabase(): Result {
        val gsonUtil = GsonUtil(applicationContext)
        val sampleCategoryList = gsonUtil.getCategoryFromJson()
        val sampleQuizList = gsonUtil.getQuizFromJson()
        val sampleChoiceList = gsonUtil.getChoiceFromJson()
        val appDatabase = AppDatabase.getInstance(applicationContext)

        appDatabase.categoryDao.saveList(sampleCategoryList)
        appDatabase.quizDao.saveList(sampleQuizList)
        appDatabase.choiceDao.saveList(sampleChoiceList)
        return Result.success()
    }
}