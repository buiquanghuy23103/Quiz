package com.example.quiz.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.quiz.database.DataGenerator

class QuizAskPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    private val itemList = DataGenerator.quizzes

    override fun getItem(position: Int): Fragment = QuizAskFragment.getInstance(position)

    override fun getCount(): Int = itemList.size
}