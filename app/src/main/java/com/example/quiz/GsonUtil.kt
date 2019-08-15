package com.example.quiz

import android.content.Context
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

const val QUIZ_JSON = "quiz.json"
const val CHOICE_JSON = "choice.json"
const val CATEGORY_JSON = "category.json"

class GsonUtil(private val appContext: Context) {

    private val gson = Gson()

    fun getCategoryFromJson() = getObjectFromJsonFile<Category>(CATEGORY_JSON)

    fun getQuizFromJson() = getObjectFromJsonFile<Quiz>(QUIZ_JSON)

    fun getChoiceFromJson() = getObjectFromJsonFile<Choice>(CHOICE_JSON)

    private fun <T> getObjectFromJsonFile(filename: String): List<T> {
        appContext.assets.open(filename).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val type = object : TypeToken<List<T>>() {}.type
                return gson.fromJson(jsonReader, type)
            }
        }
    }
}