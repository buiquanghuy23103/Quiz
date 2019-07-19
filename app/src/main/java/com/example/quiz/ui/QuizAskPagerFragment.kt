package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.quiz.R
import com.example.quiz.viewmodel.QuizAskPagerViewModel
import kotlinx.android.synthetic.main.quiz_ask_pager_fragment.*

class QuizAskPagerFragment : Fragment() {
    private val args: QuizAskPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_ask_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view_pager.adapter = QuizAskPagerAdapter(childFragmentManager, args.index)
    }

}
