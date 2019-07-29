package com.example.quiz.quizedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizEditFragmentBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseFragment
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Answer

class QuizEditFragment : BaseFragment<QuizEditViewModel>() {
    private lateinit var binding: QuizEditFragmentBinding
    private val args: QuizEditFragmentArgs by navArgs()
    private val answerEditAdapter = AnswerEditAdapter()

    override fun initViewModel() =
        getViewModel { QuizEditViewModel(args.quizId) }

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
            viewModel.finalQuizForSaving = it
        })
    }

    private fun setupAnswerView() {
        viewModel.answerList.observe(this, Observer { answerList ->
            setupAdapter(answerList)
            viewModel.finalAnswerListForSaving = answerList
            setupItemClickListener(answerList)
            setupAddAnswerButton()
        })
    }

    private fun setupAdapter(answerList: List<Answer>) {
        binding.answerEditRecyclerView.adapter = answerEditAdapter
        answerEditAdapter.submitList(answerList)
    }

    private fun setupItemClickListener(it: List<Answer>?) {
        answerEditAdapter.itemClickListener = object : BaseListItem.ClickListener {
            override fun onViewClick(position: Int) {}

            override fun onDeleteButtonClick(data: BaseData) {
                viewModel.deleteAnswer(data as Answer)
            }
        }
    }

    private fun setupAddAnswerButton() {
        binding.quizEditAddAnswerButton.setOnClickListener {
            viewModel.addAnswer()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuiz()
        viewModel.saveAnswerList()
    }
}
