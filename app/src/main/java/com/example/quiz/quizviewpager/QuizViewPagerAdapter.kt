package com.example.quiz.quizviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quiz.quiz.QuizFragment
import timber.log.Timber

class QuizViewPagerAdapter(fm: Fragment, private val quizIdList: List<Int>) :
    FragmentStateAdapter(fm) {

    override fun getItemCount() = quizIdList.size

    override fun createFragment(position: Int): Fragment {
        val id = quizIdList[position].also { Timber.i("position = $position; id = $it") }
        return QuizFragment.getInstance(id)
    }
}