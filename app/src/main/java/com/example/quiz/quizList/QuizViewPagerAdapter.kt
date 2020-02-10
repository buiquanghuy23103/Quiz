package com.example.quiz.quizList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quiz.quiz.QuizFragment
import timber.log.Timber

class QuizViewPagerAdapter(activity: FragmentActivity, private val quizIdList: List<String>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount() = quizIdList.size

    override fun createFragment(position: Int): Fragment {
        val id = quizIdList[position].also { Timber.i("position = $position; id = $it") }
        return QuizFragment.getInstance(id)
    }
}