package com.example.quiz.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizAskFragmentBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizAskViewModel
import kotlin.math.abs

class QuizAskFragment : Fragment() {
    private val TAG : String = "QuizAskFragment"
    private val args: QuizAskFragmentArgs by navArgs()
    private lateinit var binding: QuizAskFragmentBinding
    private lateinit var viewModel : QuizAskViewModel
    private lateinit var quizBank : List<Quiz>

    companion object{
        fun newInstance() = QuizAskFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_ask_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(QuizAskViewModel::class.java)
        quizBank = viewModel.quizBank
        for (i in 0..(quizBank.size-1)){
            if (quizBank[i].id == args.quizId){
                viewModel.currentIndex = i
                Log.i(TAG, "index = " + i)
                break
            }
        }
        updateQuestion()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.nextButton.setOnClickListener{
            viewModel.currentIndex = (viewModel.currentIndex + 1) % quizBank.size
            updateQuestion()
        }

        binding.backButton.setOnClickListener {
            viewModel.currentIndex = abs(viewModel.currentIndex - 1) % quizBank.size
            updateQuestion()
        }
    }

    private fun checkAnswer(userPressed: Boolean){
        if (userPressed == quizBank[viewModel.currentIndex].answer){
            toast(R.string.correct_toast)
        } else {
            toast(R.string.incorrect_toast)
        }
    }

    private fun toast(messageId : Int){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuestion(){
        binding.questTextView.text = getString(quizBank[viewModel.currentIndex].question)
    }
}