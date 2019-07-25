package com.example.quiz.quizviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.quiz.quizview.QuizViewFragment
import timber.log.Timber

class QuizViewPagerAdapter(fm: FragmentManager, private val quizIdList: List<Int>)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment{
        val id = quizIdList[position]
        return QuizViewFragment.getInstance(id)
    }

    override fun getCount(): Int = quizIdList.size

    override fun getPageTitle(position: Int): CharSequence? = "QUESTION ${(position + 1)}"
}