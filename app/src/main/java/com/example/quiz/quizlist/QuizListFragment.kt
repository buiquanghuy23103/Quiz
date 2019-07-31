package com.example.quiz.quizlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.QuizListFragmentBinding
import com.example.quiz.framework.BaseFragment

class QuizListFragment : BaseFragment<QuizListViewModel, QuizListFragmentBinding>() {

    override fun initViewModel() =
        getViewModel { QuizListViewModel() }

    override fun getLayoutId() = R.layout.quiz_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = QuizListAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.quizList.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}