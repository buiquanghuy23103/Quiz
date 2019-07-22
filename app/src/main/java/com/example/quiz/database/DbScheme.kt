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

    class AnswerTable{
        companion object{
            const val TABLE_NAME = "answerTable"
        }

        class Cols{
            companion object{
                const val ID = "id"
                const val QUIZ_ID = "quiz_id"
                const val TEXT = "text"
                const val IS_TRUE = "is_true"
            }
        }
    }
}