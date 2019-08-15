package com.example.quiz.quiz

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
import com.example.quiz.MainActivity
import com.example.quiz.R
import com.example.quiz.categorylist.CategoryListItem
import com.example.quiz.model.Quiz
import com.example.quiz.util.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuizFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)
    private lateinit var resources: Resources
    private val sampleCategory: String
    private val sampleQuizListOfSampleCategory: List<Quiz>

    init {
        val sampleCategoryMap = sampleQuizList.groupBy { quiz -> quiz.categoryId }
        sampleCategory = sampleCategoryMap.keys.toList().sorted().get(0)
        sampleQuizListOfSampleCategory =
            sampleCategoryMap[sampleCategory]?.let { it.sortedBy { quiz -> quiz.text } }
                ?: sampleQuizList
    }


    @Before
    fun jumpToQuizViewPagerFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply {
                    putString(
                        "category",
                        sampleCategory
                    )
                }
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
    fun testQuestionTextViewIsDisplayed() {
        onView(withId(R.id.quest_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testQuestionTextViewDisplayCorrectText() {
        onView(withId(R.id.quest_text_view))
            .check(matches(withText(sampleQuiz.text)))
    }

    @Test
    fun testAnswerListIsDisplayed() {
        onView(withId(R.id.answer_view_recycler_view))
            .check(matches(isDisplayed()))
    }


    @Test
    fun testChangeBackgroundColorOnAnswerButtonClick() {
        val chosenColorId = R.color.chosenAnswerColor
        val notChosenColorId = android.R.color.transparent
        checkBackgroundColor(notChosenColorId)

        onView(withId(R.id.answer_view_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CategoryListItem>(0, click()))

        checkBackgroundColor(chosenColorId)
    }

    private fun checkBackgroundColor(colorRes: Int) {
        onView(listMatcher().atPosition(0))
            .check(matches(CustomMatchers.hasBackgroundColor(colorRes)))
    }

    private fun listMatcher() = RecyclerViewMatcher(R.id.answer_view_recycler_view)

    @Test
    fun testResultViewVisibility() {
        onView(withId(R.id.result_text_view))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.check_answer_button))
            .perform(click())

        onView(withId(R.id.result_text_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testResultViewText() {
        val indexOfCorrectAnswers = sampleAnswersOfSampleQuiz.mapIndexed { index, answer ->
            if (answer.isTrue) index else 0
        }.filter { it != 0 }.also { println(it) }


        indexOfCorrectAnswers.forEach {
            onView(withId(R.id.answer_view_recycler_view))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<CategoryListItem>(
                        it,
                        click()
                    )
                )
        }

        onView(withId(R.id.check_answer_button))
            .perform(click())

        onView(withId(R.id.result_text_view))
            .check(matches(withText(resources.getString(R.string.correct_answer))))
    }
}