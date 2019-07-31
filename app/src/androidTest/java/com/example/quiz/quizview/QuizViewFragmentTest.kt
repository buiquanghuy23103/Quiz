package com.example.quiz.quizview

import android.content.res.Resources
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.quiz.*
import com.example.quiz.quizlist.QuizItemHolder
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
    private lateinit var resources: Resources

    @Before
    fun jumpToQuizViewPagerFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putInt("index", sampleQuizList.indexOf(sampleQuiz)) }
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.quizViewPagerFragment,
                    bundle
                )
            }
        }
    }

    @Before
    fun initResources() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        resources = context.resources
    }

    @Test
    fun testAnswerListIsDisplayed() {
        onView(allOf(withId(R.id.answer_view_recycler_view)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCheckAnswerButton() {
        val trueIndices = sampleAnswersOfSampleQuiz.filter { answer -> answer.isTrue }
            .map { answer -> sampleAnswersOfSampleQuiz.indexOf(answer) }
        onView(allOf(withId(R.id.answer_view_recycler_view)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<QuizItemHolder>(
                    trueIndices[0],
                    click()
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<QuizItemHolder>(
                    trueIndices[1],
                    click()
                )
            )
        onView(allOf(withId(R.id.check_answer_button)))
            .perform(click())
        onView(allOf(withId(R.id.result_text_view)))
            .check(matches(withText(resources.getString(R.string.trueAnswer))))
    }
}