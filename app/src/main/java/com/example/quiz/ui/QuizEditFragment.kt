package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.quiz.R
import com.example.quiz.databinding.QuizEditFragmentBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizEditViewModel
import com.example.quiz.viewmodel.QuizEditViewModelFactory
import kotlinx.android.synthetic.main.quiz_ask_fragment.*

class QuizEditFragment : Fragment() {
    private val TAG = "QuizEditFragment"
    private lateinit var viewModel: QuizEditViewModel
    private lateinit var binding: QuizEditFragmentBinding
    private val args: QuizEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_edit_fragment, container, false)

        val app = requireNotNull(this.activity).application
        val factory = QuizEditViewModelFactory(app, args.quizId)
        viewModel = ViewModelProviders.of(this, factory).get(QuizEditViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.quiz = viewModel.quiz
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveQuiz().also { Log.i(TAG, "save quiz") }
    }
}
