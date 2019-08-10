package com.example.quiz.quiz

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.QuizFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_fragment.*
import timber.log.Timber

class QuizFragment : BaseFragment<QuizViewModel, QuizFragmentBinding>(),
    ChoiceListItem.UIinterface {
    private val choiceListAdapter = ChoiceListAdapter()

    companion object{
        private const val ARG_QUIZ_ID = "index"
        fun getInstance(quizId: Int): QuizFragment {
            return QuizFragment().apply {
                arguments = Bundle().apply { putInt(ARG_QUIZ_ID, quizId) }
            }
        }
    }

    override fun initViewModel() =
        getViewModel { QuizViewModel(getQuizId().also { Timber.i("quizId = $it") }) }

    override fun getLayoutId() = R.layout.quiz_fragment

    private fun getQuizId(): Int {
        return arguments!!.takeIf { it.containsKey(ARG_QUIZ_ID) }
            ?.getInt(ARG_QUIZ_ID)
            ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupQuizView()
        setupChoiceView()
        setupCheckButton()
        setupResultView()
    }

    private fun setupQuizView() {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })
    }

    private fun setupChoiceView() {
        answer_view_recycler_view.adapter = choiceListAdapter
        viewModel.choiceList.observe(this, Observer { choiceList ->
            choiceListAdapter.submitList(choiceList)
            println("choiceList = $choiceList")
        })
        choiceListAdapter.itemClickListener = this
    }

    private fun setupCheckButton() {
        check_answer_button.setOnClickListener {
            result_text_view.visibility = View.VISIBLE
        }
    }

    private fun setupResultView() {
        viewModel.assessment.observe(this, Observer { isCorrect ->
            result_text_view.text = if (isCorrect) getString(R.string.correct_answer)
            else getString(R.string.incorrect_answer)
        })
    }

    override fun onClick(answerId: Int) {
        viewModel.toggleChoiceChosenById(answerId)
    }

    override fun setBackgroundColor(view: View, answerId: Int) {
        viewModel.getChoiceById(answerId).observe(this, Observer {
            val chosenAnswerColor = ContextCompat.getColor(view.context, R.color.chosenAnswerColor)
            val notChosenAnswerColor =
                ContextCompat.getColor(view.context, android.R.color.transparent)
            val backgroundColor = if (it.isChosen) chosenAnswerColor else notChosenAnswerColor
            view.setBackgroundColor(backgroundColor)
        })
    }
}