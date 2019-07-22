package com.example.quiz.quizaskpager

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.BaseFragment

import com.example.quiz.R
import kotlinx.android.synthetic.main.quiz_ask_pager_fragment.*

class QuizAskPagerFragment : BaseFragment<QuizAskPagerViewModel>() {
    private val args: QuizAskPagerFragmentArgs by navArgs()

    override fun initViewModel(): QuizAskPagerViewModel {
        return ViewModelProviders.of(this).get(QuizAskPagerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_ask_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quizIdList.observe(this, Observer {
            var adapter = QuizAskPagerAdapter(childFragmentManager, it)
            view_pager.adapter = adapter
            view_pager.currentItem = args.index
        })
    }
}
