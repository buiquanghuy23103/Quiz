package com.example.quiz.quizaskpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.quiz.model.Quiz
import com.example.quiz.quizask.QuizAskFragment

class QuizAskPagerAdapter(fm: FragmentManager, private val quizIdList: List<Int>)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment{
        val id = quizIdList[position]
        return QuizAskFragment.getInstance(id)
    }

    override fun getCount(): Int = quizIdList.size

    override fun getPageTitle(position: Int): CharSequence? = "QUESTION ${(position + 1)}"
}