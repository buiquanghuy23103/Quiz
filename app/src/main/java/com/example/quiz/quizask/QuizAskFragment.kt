package com.example.quiz.quizask

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.databinding.QuizAskFragmentBinding

class QuizAskFragment : Fragment() {
    private lateinit var binding: QuizAskFragmentBinding

    private val viewModel: QuizAskViewModel by lazy {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_INDEX) }
        val index = arg?.let { it.getInt(ARG_INDEX) } ?: 0

        val factory = QuizAskViewModelFactory(index)
        ViewModelProviders.of(this, factory).get(QuizAskViewModel::class.java)
    }

    companion object{
        private const val ARG_INDEX = "index"
        fun getInstance(index: Int): QuizAskFragment {
            return QuizAskFragment().apply {
                arguments = Bundle().apply { putInt(ARG_INDEX, index) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_ask_fragment, container, false)

        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })

        return binding.root
    }

    private fun initViewModel() {

    }
}