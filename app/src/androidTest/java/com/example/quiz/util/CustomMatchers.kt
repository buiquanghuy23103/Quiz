package com.example.quiz.util

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.internal.util.Checks
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class CustomMatchers {
    companion object {
        fun hasBackgroundColor(colorRes: Int): Matcher<View> {
            Checks.checkNotNull(colorRes)

            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("background color: $colorRes")
                }

                override fun matchesSafely(item: View?): Boolean {

                    val context = item?.context
                    val textViewColor = (item?.background as ColorDrawable).color
                    val expectedColor = context?.getColor(colorRes)

                    return textViewColor == expectedColor
                }
            }
        }
    }
}