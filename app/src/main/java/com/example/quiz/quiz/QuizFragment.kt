package com.example.quiz.quiz

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.QuizFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_fragment.*

class QuizFragment : BaseFragment<QuizViewModel, QuizFragmentBinding>(),
    AnswerListItem.OnClickListener {
    private val answerAdapter = AnswerListAdapter()

    companion object{
        private const val ARG_QUIZ_ID = "index"
        fun getInstance(quizId: Int): QuizFragment {
            return QuizFragment().apply {
                arguments = Bundle().apply { putInt(ARG_QUIZ_ID, quizId) }
            }
        }
    }

    override fun initViewModel() =
        getViewModel { QuizViewModel(getQuizId()) }

    override fun getLayoutId() = R.layout.quiz_fragment

    private fun getQuizId(): Int {
        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_QUIZ_ID) }
        return arg?.let { it.getInt(ARG_QUIZ_ID) } ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupQuizView()
        setupAnswerView()
        setupCheckAnswerButton()
    }

    private fun setupQuizView() {
        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })
    }

    private fun setupAnswerView() {
        answer_view_recycler_view.adapter = answerAdapter
        viewModel.answerList.observe(this, Observer { answerList ->
            answerAdapter.submitList(answerList)
        })
        answerAdapter.itemClickListener = this
    }

    private fun setupCheckAnswerButton() {
        check_answer_button.setOnClickListener {
            result_text_view.text =
                if (viewModel.isCorrectAnswer()) getString(R.string.correct_answer)
                else getString(R.string.incorrect_answer)
        }
    }

    override fun onClick(view: View, answerId: Int) {
        changeButtonColor(view)
        viewModel.toggleAnswerChosenById(answerId)
    }

    private fun changeButtonColor(view: View) {
        val chosenAnswerColor = ContextCompat.getColor(view.context, R.color.chosenAnswerColor)
        val notChosenAnswerColor = ContextCompat.getColor(view.context, android.R.color.transparent)
        val currentColor = (view.background as ColorDrawable?)?.color

        if (currentColor == chosenAnswerColor) {
            view.setBackgroundColor(notChosenAnswerColor)
            return
        }
        view.setBackgroundColor(chosenAnswerColor)
    }
}