package com.example.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.quiz.R

class MainActivity : BaseActivity() {
    override fun fragment(): Fragment {
        return QuizAnswerFragment.newInstance()
    }
}