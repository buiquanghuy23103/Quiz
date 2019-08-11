package com.example.quiz.data.local

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.quiz.GsonUtil
import timber.log.Timber


class SeedDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return generateData()
    }

    private fun generateData(): Result {
        try {
            return insertSampleDataToDatabase()
        } catch (ex: Exception) {
            Timber.e("Error seeding database: $ex")
            return Result.failure()
        }
    }

    @Throws(Exception::class)
    private fun insertSampleDataToDatabase(): Result {
        val gsonUtil = GsonUtil(applicationContext)
        val sampleQuizList = gsonUtil.getQuizFromJson()
        val sampleChoiceList = gsonUtil.getChoiceFromJson()
        val appDatabase = AppDatabase.getInstance(applicationContext)

        appDatabase.quizDao.saveList(sampleQuizList)
        appDatabase.choiceDao.saveList(sampleChoiceList)
        return Result.success()
    }
}