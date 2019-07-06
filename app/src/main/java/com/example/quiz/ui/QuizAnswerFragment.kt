package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.quiz.R
import com.example.quiz.viewmodel.QuizAnswerViewModel

class QuizAnswerFragment : Fragment() {

    companion object {
        fun newInstance() = QuizAnswerFragment()
    }

    private lateinit var viewModel: QuizAnswerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_answer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuizAnswerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
