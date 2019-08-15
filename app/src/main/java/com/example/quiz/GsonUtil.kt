package com.example.quiz

import android.content.Context
import com.example.quiz.model.Category
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.lang.reflect.Type

const val QUIZ_JSON = "quiz.json"
const val CHOICE_JSON = "choice.json"
const val CATEGORY_JSON = "category.json"

// Generics on a type are typically erased at runtime, except when the type is compiled with the generic parameter bound. In that case, the compiler inserts the generic type information into the compiled class. In other cases, that is not possible.
// Source: https://stackoverflow.com/questions/14503881/strange-behavior-when-deserializing-nested-generic-classes-with-gson/14506181#14506181

class GsonUtil(private val appContext: Context) {

    private val gson = Gson()

    fun getCategoryFromJson(): List<Category> {
        val type = object : TypeToken<List<Category>>() {}.type
        return getObjectFromJsonFile(type, CATEGORY_JSON)
    }

    fun getQuizFromJson(): List<Quiz> {
        val type = object : TypeToken<List<Quiz>>() {}.type
        return getObjectFromJsonFile(type, QUIZ_JSON)
    }

    fun getChoiceFromJson(): List<Choice> {
        val type = object : TypeToken<List<Choice>>() {}.type
        return getObjectFromJsonFile(type, CHOICE_JSON)
    }

    private fun <T> getObjectFromJsonFile(type: Type, filename: String): List<T> {
        appContext.assets.open(filename).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                return gson.fromJson(jsonReader, type)
            }
        }
    }
}