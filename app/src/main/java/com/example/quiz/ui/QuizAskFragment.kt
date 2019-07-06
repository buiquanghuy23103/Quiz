package com.example.quiz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quiz.R
import com.example.quiz.database.Quiz
import com.example.quiz.databinding.FragmentQuizAskBinding

class QuizAskFragment : Fragment() {
    private lateinit var binding: FragmentQuizAskBinding
    private var currentIndex : Int = 0
    private val quizBank = listOf<Quiz>(
        Quiz(R.string.question_africa, false),
        Quiz(R.string.question_australia, true),
        Quiz(R.string.question_oceans, true),
        Quiz(R.string.question_mideast, false),
        Quiz(R.string.question_americas, true),
        Quiz(R.string.question_asia, true)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_ask, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % quizBank.size
            updateQuestion()
        }

        binding.backButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % quizBank.size
            updateQuestion()
        }
    }

    private fun checkAnswer(userPressed: Boolean){
        if (userPressed == quizBank[currentIndex].answer){
            toast(R.string.correct_toast)
        } else {
            toast(R.string.incorrect_toast)
        }
    }

    private fun toast(messageId : Int){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuestion(){
        binding.questTextView.text = getString(quizBank[currentIndex].question)
    }
}