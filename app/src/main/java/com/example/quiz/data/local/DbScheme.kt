package com.example.quiz.data.local

object DbScheme {
    const val DATABASE_NAME = "quizDatabase.db"

    object QuizTable {
        const val TABLE_NAME = "quizTable"

        object Cols {
            const val ID = "id"
            const val QUESTION = "text"
            const val CATEGORY = "category"
        }
    }

    object AnswerTable {
        const val TABLE_NAME = "answerTable"

        object Cols {
            const val ID = "id"
            const val QUIZ_ID = "quiz_id"
            const val TEXT = "text"
            const val IS_TRUE = "is_true"
            const val IS_CHOSEN = "is_chosen"
        }
    }
}