package com.example.quiz.quizview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.BaseFragment
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewFragmentBinding
import kotlinx.android.synthetic.main.quiz_view_fragment.*

class QuizViewFragment : BaseFragment<QuizViewViewModel>() {
    private lateinit var binding: QuizViewFragmentBinding

    companion object{
        private const val ARG_QUIZ_ID = "index"
        fun getInstance(quizId: Int): QuizViewFragment {
            return QuizViewFragment().apply {
                arguments = Bundle().apply { putInt(ARG_QUIZ_ID, quizId) }
            }
        }
    }

    override fun initViewModel(): QuizViewViewModel {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_QUIZ_ID) }
        val quizId = arg?.let { it.getInt(ARG_QUIZ_ID) } ?: 0

        val factory = QuizViewViewModelFactory(quizId)
        return ViewModelProviders.of(this, factory).get(QuizViewViewModel::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_view_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })

        viewModel.answerList.observe(this, Observer {
            answer_view_recycler_view.adapter = AnswerViewAdapter(it)
        })
    }
}