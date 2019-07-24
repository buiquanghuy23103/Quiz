package com.example.quiz.quizviewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.quiz.quizview.QuizViewFragment

class QuizViewPagerAdapter(fm: FragmentManager, private val quizIdList: List<Int>)
    : FragmentStatePagerAdapter(fm) {

    var currentQuizId = 0

    override fun getItem(position: Int): Fragment{
        currentQuizId = quizIdList[position]
        return QuizViewFragment.getInstance(currentQuizId)
    }

    override fun getCount(): Int = quizIdList.size

    override fun getPageTitle(position: Int): CharSequence? = "QUESTION ${(position + 1)}"
}