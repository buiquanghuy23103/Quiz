package com.example.quiz.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.database.Quiz
import com.example.quiz.databinding.FragmentQuizAskBinding
import com.example.quiz.model.QuizAskViewModel

class QuizAskFragment : Fragment() {
    private val TAG : String = "QuizAskFragment"
    private lateinit var binding: FragmentQuizAskBinding
    private lateinit var model : QuizAskViewModel
    private lateinit var quizBank : List<Quiz>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_ask, container, false)
        model = ViewModelProviders.of(this).get(QuizAskViewModel::class.java)
        quizBank = model.quizBank
        updateQuestion()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.nextButton.setOnClickListener{
            model.currentIndex = (model.currentIndex + 1) % quizBank.size
            updateQuestion()
        }

        binding.backButton.setOnClickListener {
            model.currentIndex = (model.currentIndex - 1) % quizBank.size
            updateQuestion()
        }
    }

    private fun checkAnswer(userPressed: Boolean){
        if (userPressed == quizBank[model.currentIndex].answer){
            toast(R.string.correct_toast)
        } else {
            toast(R.string.incorrect_toast)
        }
    }

    private fun toast(messageId : Int){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuestion(){
        binding.questTextView.text = getString(quizBank[model.currentIndex].question)
    }
}