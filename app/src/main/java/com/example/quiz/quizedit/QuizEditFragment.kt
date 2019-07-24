package com.example.quiz.quizedit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.BaseFragment

import com.example.quiz.R
import com.example.quiz.databinding.QuizEditFragmentBinding
import kotlinx.android.synthetic.main.quiz_edit_fragment.*

class QuizEditFragment : BaseFragment<QuizEditViewModel>() {
    private lateinit var binding: QuizEditFragmentBinding
    private val args: QuizEditFragmentArgs by navArgs()

    override fun initViewModel(): QuizEditViewModel {
        val factory = QuizEditViewModelFactory(args.quizId)
        return ViewModelProviders.of(this, factory).get(QuizEditViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_edit_fragment, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quiz.observe(this, Observer {
            binding.quiz = it
            viewModel.quizSync = it
        })

        val answerEditAdapter = AnswerEditAdapter()

        viewModel.answerList.observe(this, Observer {
            answer_edit_recycler_view.adapter = answerEditAdapter
            answerEditAdapter.answerList = it
            viewModel.answerListSync = it
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuiz()
        viewModel.saveAnswerList()
    }
}
