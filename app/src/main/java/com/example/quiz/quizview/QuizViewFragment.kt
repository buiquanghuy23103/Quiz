package com.example.quiz.quizview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewFragmentBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseFragment
import com.example.quiz.framework.BaseListItem
import kotlinx.android.synthetic.main.quiz_view_fragment.*

class QuizViewFragment : BaseFragment<QuizViewViewModel>() {
    private lateinit var binding: QuizViewFragmentBinding
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

    private fun getQuizId(): Int {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_QUIZ_ID) }
        return arg?.let { it.getInt(ARG_QUIZ_ID) } ?: 0
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_view_fragment, container, false)
        return binding.root
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
        answerAdapter.itemClickListener = object : BaseListItem.ClickListener {
            override fun onViewClick(position: Int) {}
            override fun onDeleteButtonClick(data: BaseData) {}
        }
    }

    private fun setupQuizView() {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })
    }
}