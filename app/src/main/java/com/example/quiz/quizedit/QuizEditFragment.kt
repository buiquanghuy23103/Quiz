package com.example.quiz.quizedit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.example.quiz.R
import com.example.quiz.databinding.QuizEditFragmentBinding

class QuizEditFragment : Fragment() {
    private lateinit var viewModel: QuizEditViewModel
    private lateinit var binding: QuizEditFragmentBinding
    private val args: QuizEditFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_edit_fragment, container, false)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        val app = requireNotNull(this.activity).application
        val factory = QuizEditViewModelFactory(app, args.quizId)
        viewModel = ViewModelProviders.of(this, factory).get(QuizEditViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.quiz = viewModel.quiz
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuiz()
    }
}
