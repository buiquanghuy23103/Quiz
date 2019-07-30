package com.example.quiz.quizviewpager

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewPagerFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel, QuizViewPagerFragmentBinding>() {
    private val args: QuizViewPagerFragmentArgs by navArgs()
    lateinit var adapter : QuizViewPagerAdapter

    override fun initViewModel() =
        getViewModel { QuizViewPagerViewModel() }

    override fun getLayoutId() = R.layout.quiz_view_pager_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quizIdList.observe(this, Observer { quizList ->
            updateUI(quizList)
        })
    }

    private fun updateUI(quizIdList: List<Int>) {
        setupAdapter(quizIdList)
        view_pager.currentItem = args.index
        setupEditButton(quizIdList)
    }

    private fun setupEditButton(quizIdList: List<Int>) {
        quiz_view_pager_fab.setOnClickListener {
            val id = quizIdList[view_pager.currentItem]
            val directions = QuizViewPagerFragmentDirections
                .actionQuizAskPagerFragmentToQuizEditFragment(id)
            this.findNavController().navigate(directions)
        }
    }

    private fun setupAdapter(quizIdList: List<Int>) {
        adapter = QuizViewPagerAdapter(childFragmentManager, quizIdList)
        view_pager.adapter = adapter
    }
}
