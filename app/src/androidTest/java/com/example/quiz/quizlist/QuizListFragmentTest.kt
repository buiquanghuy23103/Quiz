package com.example.quiz.quizlist

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.quiz.MainActivity
import com.example.quiz.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuizListFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToQuizListFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                findNavController(R.id.nav_host_fragment).navigate(R.id.quizListFragment)
            }
        }
    }

    @Test
    fun testQuizListIsDisplayed() {
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))
    }
}