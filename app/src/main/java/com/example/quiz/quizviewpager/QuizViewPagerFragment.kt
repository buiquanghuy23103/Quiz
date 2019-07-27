package com.example.quiz.quizviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel>() {
    private val args: QuizViewPagerFragmentArgs by navArgs()
    lateinit var adapter : QuizViewPagerAdapter

    override fun initViewModel(): QuizViewPagerViewModel {
        return ViewModelProviders.of(this).get(QuizViewPagerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.quiz_view_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quizIdList.observe(this, Observer {
            updateUI(it)
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
