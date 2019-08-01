package com.example.quiz

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CheckAnswerUtilsTest {

    private lateinit var checkAnswerUtils: CheckAnswerUtils
    private lateinit var indexOfCorrectAnswers: List<Int>

    @Before
    fun setUp() {
        checkAnswerUtils = CheckAnswerUtils(testAnswerList)
        indexOfCorrectAnswers = testAnswerList.filter { answer -> answer.isTrue }
            .map { answer -> testAnswerList.indexOf(answer) }
    }

    @Test
    fun result() {
        indexOfCorrectAnswers.forEach { index ->
            checkAnswerUtils.saveUserSelectionAtPosition(index)
        }

        assertThat(checkAnswerUtils.result()).isTrue()
    }
}