package com.example.quiz

import android.content.Context
import com.example.quiz.model.Choice
import com.example.quiz.model.Quiz
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

const val QUIZ_JSON = "quiz.json"
const val CHOICE_JSON = "choice.json"

class GsonUtil(private val appContext: Context) {
    val gson = Gson()

    fun getQuizFromJson(): List<Quiz> {
        appContext.assets.open(QUIZ_JSON).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val quizType = object : TypeToken<List<Quiz>>() {}.type
                return gson.fromJson(jsonReader, quizType)
            }
        }
    }

    fun getChoiceFromJson(): List<Choice> {
        appContext.assets.open(CHOICE_JSON).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val choiceType = object : TypeToken<List<Choice>>() {}.type
                return gson.fromJson(jsonReader, choiceType)
            }
        }
    }
}