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
import com.example.quiz.model.Answer
import kotlinx.android.synthetic.main.quiz_edit_fragment.*
import timber.log.Timber

class QuizEditFragment : BaseFragment<QuizEditViewModel>() {
    private lateinit var binding: QuizEditFragmentBinding
    private val args: QuizEditFragmentArgs by navArgs()
    val answerEditAdapter = AnswerEditAdapter()

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
        setupQuizView()
        setupAnswerView()
    }

    private fun setupQuizView() {
        viewModel.quiz.observe(this, Observer {
            binding.quiz = it
            viewModel.quizSync = it
        })
    }

    private fun setupAnswerView() {
        viewModel.answerList.observe(this, Observer {
            setupAdapter(it)
            setupItemClickListener(it)
            setupAddAnswerButton(it)
            viewModel.answerListSync = it
        })
    }

    private fun setupAdapter(it: List<Answer>) {
        binding.answerEditRecyclerView.adapter = answerEditAdapter
        answerEditAdapter.answerList = it
    }

    private fun setupItemClickListener(it: List<Answer>?) {
        answerEditAdapter.itemClickListener = object : AnswerEditItem.OnListItemClickListener {
            override fun onDeleteClick(answer: Answer) {
                (it as ArrayList<Answer>).remove(answer)
                answerEditAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupAddAnswerButton(answerList: List<Answer>){
        val newAnswer = Answer(viewModel.quizId, "New answer", true)
        binding.quizEditAddAnswerButton.setOnClickListener {
            (answerList as ArrayList<Answer>).add(newAnswer)
            answerEditAdapter.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuiz()
        viewModel.saveAnswerList()
    }
}
