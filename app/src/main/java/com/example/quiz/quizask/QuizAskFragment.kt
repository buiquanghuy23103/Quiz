package com.example.quiz.quizask

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.BaseFragment
import com.example.quiz.R
import com.example.quiz.databinding.QuizAskFragmentBinding
import kotlinx.android.synthetic.main.quiz_ask_fragment.*

class QuizAskFragment : BaseFragment<QuizAskViewModel>() {
    private lateinit var binding: QuizAskFragmentBinding

    companion object{
        private const val ARG_INDEX = "index"
        fun getInstance(quizId: Int): QuizAskFragment {
            return QuizAskFragment().apply {
                arguments = Bundle().apply { putInt(ARG_INDEX, quizId) }
            }
        }
    }

    override fun initViewModel(): QuizAskViewModel {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_INDEX) }
        val index = arg?.let { it.getInt(ARG_INDEX) } ?: 0

        val factory = QuizAskViewModelFactory(index)
        return ViewModelProviders.of(this, factory).get(QuizAskViewModel::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_ask_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })

        viewModel.answerList.observe(this, Observer {
            answer_recycler_view.adapter = AnswerAdapter(it)
        })
    }
}