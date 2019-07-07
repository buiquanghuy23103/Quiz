package com.example.quiz.ui

import androidx.fragment.app.Fragment

class MainActivity : BaseActivity() {
    override fun fragment(): Fragment {
        return QuizListFragment.newInstance()
    }
}