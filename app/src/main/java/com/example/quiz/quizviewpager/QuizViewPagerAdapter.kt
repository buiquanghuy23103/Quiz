package com.example.quiz.quizviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quiz.quiz.QuizFragment

class QuizViewPagerAdapter(fm: Fragment, private val quizIdList: List<Int>) :
    FragmentStateAdapter(fm) {

    override fun getItemCount() = quizIdList.size

    override fun createFragment(position: Int): Fragment {
        val id = quizIdList[position]
        return QuizFragment.getInstance(id)
    }
}