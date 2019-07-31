package com.example.quiz

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

// Source: https://github.com/dannyroa/espresso-samples/blob/master/RecyclerView/app/src/androidTest/java/com/dannyroa/espresso_samples/recyclerview/RecyclerViewMatcher.java
class RecyclerViewMatchers(@IdRes private val recyclerViewId: Int) {
    private var resources: Resources? = null

    fun atPosition(position: Int): Matcher<View> {
        return atChildViewAtPosition(-1, position)
    }

    fun atChildViewAtPosition(@IdRes targetViewId: Int, position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                val recyclerViewName = resources?.getResourceName(recyclerViewId)
                description?.appendText("Resource name not found $recyclerViewName")
            }

            override fun matchesSafely(item: View?): Boolean {
                resources = item?.resources
                val childView = getViewHolderAtPosition(item, position)
                return if (targetViewId == -1)
                    item == childView
                else
                    item == childView?.findViewById(targetViewId)
            }
        }
    }

    private fun getViewHolderAtPosition(view: View?, position: Int): View? {
        val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
        return recyclerView?.findViewHolderForLayoutPosition(position)?.itemView
    }

}