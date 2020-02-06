package com.example.quiz.quiz

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.QuizFragmentBinding
import com.example.quiz.framework.BaseFragment
import com.example.quiz.quizviewpager.QuizViewPagerFragmentDirections
import kotlinx.android.synthetic.main.quiz_fragment.*
import timber.log.Timber

class QuizFragment : BaseFragment<QuizViewModel, QuizFragmentBinding>(),
    ChoiceListItem.Listener
{
    private val choiceListAdapter = ChoiceListAdapter()
    private var firstOptionIsClicked = MutableLiveData<Boolean>(false)

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
        setupExplanationButton()
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
        firstOptionIsClicked.observe(viewLifecycleOwner, Observer {
            binding.firstOptionIsClicked = it
        })
        viewModel.assessment.observe(this, Observer { isCorrect ->
            binding.resultText = if (isCorrect) getString(R.string.correct_answer)
            else getString(R.string.incorrect_answer)
            binding.isCorrect = isCorrect
        })
    }

    private fun setupExplanationButton() {
        explanation_button.setOnClickListener {
            val direction =
                QuizViewPagerFragmentDirections.actionQuizViewPagerFragmentToExplanationFragment(
                    getQuizId()
                )
            it.findNavController().navigate(direction)
        }
    }

    override fun onOptionButtonClick(choiceId: Int) {
        viewModel.toggleChoiceChosenById(choiceId)
        firstOptionIsClicked.value = true
    }

    override fun setOptionButtonColor(view: View, choiceId: Int) {
        viewModel.getChosenStateById(choiceId).observe(this, Observer { isChosen ->
            val chosenColor = ContextCompat.getColor(view.context, R.color.accent)
            val notChosenColor =
                ContextCompat.getColor(view.context, R.color.primary)
            val backgroundColor = if (isChosen) chosenColor else notChosenColor
            view.setBackgroundColor(backgroundColor)
        })
    }
}