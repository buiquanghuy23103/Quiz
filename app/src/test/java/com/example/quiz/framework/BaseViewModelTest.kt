package com.example.quiz.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {


    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @Test
    fun getQuizDao() {
    }

    @Test
    fun getChoiceDao() {
    }

    @Test
    fun getIoScope() {
    }

    @Test
    fun onCleared() {
    }
}