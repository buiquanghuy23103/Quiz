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
    ChoiceListItem.Listener {
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

    private fun setupResultView() {
        viewModel.assessment.observe(this, Observer { isCorrect ->
            binding.resultText = if (isCorrect) getString(R.string.correct_answer)
            else getString(R.string.incorrect_answer)
            binding.isCorrect = isCorrect
        })
    }

    override fun onClick(choiceId: Int) {
        viewModel.toggleChoiceChosenById(choiceId)
    }

    override fun setBackgroundColor(view: View, choiceId: Int) {
        viewModel.getChoseStateById(choiceId).observe(this, Observer {
            val chosenAnswerColor = ContextCompat.getColor(view.context, R.color.chosenAnswerColor)
            val notChosenAnswerColor =
                ContextCompat.getColor(view.context, android.R.color.transparent)
            val backgroundColor = if (it) chosenAnswerColor else notChosenAnswerColor
            view.setBackgroundColor(backgroundColor)
        })
    }
}