package com.example.quiz.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.quiz.database.DataGenerator
import timber.log.Timber

class QuizAskPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    var listSize: Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment = QuizAskFragment.getInstance(position)

    override fun getCount(): Int = listSize

    override fun getPageTitle(position: Int): CharSequence? = "QUESTION ${(position + 1)}"
}