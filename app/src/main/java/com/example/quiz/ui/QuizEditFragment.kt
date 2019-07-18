package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.quiz.R
import com.example.quiz.viewmodel.QuizEditViewModel

class QuizEditFragment : Fragment() {

    companion object {
        fun newInstance() = QuizEditFragment()
    }

    private lateinit var viewModel: QuizEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuizEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
