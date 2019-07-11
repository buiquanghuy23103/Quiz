package com.example.quiz.database

class DbScheme {
    companion object{
        val DATABASE_NAME = "quizBase.db"
    }

    class QuizTable{
        companion object{
            val TABLE_NAME = "quizTable"
        }

        class Cols{
            companion object{
                val ID = "id"
                val QUESTION = "question"
                val ANSWER = "answer"
            }
        }
    }
}