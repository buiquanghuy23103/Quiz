package com.example.quiz.database

class DbScheme {
    companion object{
        const val DATABASE_NAME = "quizBase.db"
    }

    class QuizTable{
        companion object{
            const val TABLE_NAME = "quizTable"
        }

        class Cols{
            companion object{
                const val ID = "id"
                const val QUESTION = "question"
                const val ANSWER = "answer"
            }
        }
    }
}