package com.example.quiz.quizview

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.quiz.MainActivity
import com.example.quiz.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuizViewFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToQuizViewFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putInt("index", 2) }
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.quizViewPagerFragment,
                    bundle
                )
            }
        }
    }

    @Test
    fun testAnswerListIsDisplayed() {
        onView(allOf(withId(R.id.answer_view_recycler_view)))
            .check(matches(isDisplayed()))
    }
}