package com.example.quiz

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class CustomMatchers {
    companion object {
        fun hasBackgroundColor(@ColorRes colorRes: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("background color $colorRes")
                }

                override fun matchesSafely(item: View?): Boolean {
                    val expectedColor = item?.context?.getColor(colorRes)
                    val itemColor = (item?.background as ColorDrawable).color

                    return itemColor == expectedColor
                }
            }
        }
    }
}