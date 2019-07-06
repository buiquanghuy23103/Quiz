package com.example.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.quiz.R

class QuizAskActivity : BaseActivity() {
    override fun fragment(): Fragment {
        return QuizAskFragment()
    }
}