package com.example.quiz.data.local

object DbScheme {
    const val DATABASE_NAME = "quizDatabase.db"

    object QuizTable {
        const val TABLE_NAME = "quizTable"

        object Cols {
            const val ID = "id"
            const val TEXT = "text"
            const val CATEGORY_ID = "category_id"
        }
    }

    object CategoryTable {
        const val TABLE_NAME = "categoryTable"

        object Cols {
            const val ID = "id"
            const val TEXT = "text"
            const val IMAGE_URL = "image_url"
        }
    }

    object ChoiceTable {
        const val TABLE_NAME = "choiceTable"

        object Cols {
            const val ID = "id"
            const val QUIZ_ID = "quiz_id"
            const val TEXT = "text"
            const val IS_TRUE = "is_true"
        }
    }
}