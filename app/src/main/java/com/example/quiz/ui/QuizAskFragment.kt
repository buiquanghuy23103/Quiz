package com.example.quiz.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizAskFragmentBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizAskViewModel
import com.example.quiz.viewmodel.QuizAskViewModelFactory
import kotlinx.android.synthetic.main.quiz_ask_fragment.*
import kotlin.math.abs

class QuizAskFragment : Fragment() {
    private val TAG : String = "QuizAskFragment"
    private val args: QuizAskFragmentArgs by navArgs()
    private lateinit var binding: QuizAskFragmentBinding
    private lateinit var viewModel : QuizAskViewModel

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        requireNotNull(inflater).inflate(R.menu.quiz_ask_fragment, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_ask_fragment, container, false)

        val app = requireNotNull(this.activity).application
        val factory = QuizAskViewModelFactory(app, args.quizId)
        viewModel = ViewModelProviders.of(this, factory).get(QuizAskViewModel::class.java)
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { updateUI(quiz) }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateUI(quiz: Quiz){
        binding.quiz = quiz
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }
        binding.nextButton.setOnClickListener{ viewModel.moveForward() }
        binding.backButton.setOnClickListener { viewModel.moveBack() }
    }

    private fun checkAnswer(userPressed: Boolean){
        if (userPressed == true){
            toast(R.string.correct_toast)
        } else {
            toast(R.string.incorrect_toast)
        }
    }

    private fun toast(messageId : Int){
        Toast.makeText(activity, messageId, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_item_quiz_ask_edit -> startQuizEditFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startQuizEditFragment(){
        val newQuiz = Quiz()
        val action = QuizListFragmentDirections.actionQuizListFragmentToQuizEditFragment(newQuiz.id)
        this.view!!.findNavController().navigate(action)
    }
}