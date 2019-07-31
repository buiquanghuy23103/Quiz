package com.example.quiz.quizview

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_view_fragment.*

class QuizViewFragment : BaseFragment<QuizViewViewModel, QuizViewFragmentBinding>() {
    private val answerAdapter = AnswerViewAdapter()

    companion object{
        private const val ARG_QUIZ_ID = "index"
        fun getInstance(quizId: Int): QuizViewFragment {
            return QuizViewFragment().apply {
                arguments = Bundle().apply { putInt(ARG_QUIZ_ID, quizId) }
            }
        }
    }

    override fun initViewModel() =
        getViewModel { QuizViewViewModel(getQuizId()) }

    override fun getLayoutId() = R.layout.quiz_view_fragment

    private fun getQuizId(): Int {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_QUIZ_ID) }
        return arg?.let { it.getInt(ARG_QUIZ_ID) } ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupQuizView()
        setupAnswerView()
    }

    private fun setupAnswerView() {
        answer_view_recycler_view.adapter = answerAdapter
        viewModel.answerList.observe(this, Observer { answerList ->
            answerAdapter.submitList(answerList)
        })
    }

    private fun setupQuizView() {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })
    }
}