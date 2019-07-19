package com.example.quiz.quizask

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.QuizAskFragmentBinding

class QuizAskFragment : Fragment() {
    private lateinit var binding: QuizAskFragmentBinding
    private lateinit var viewModel : QuizAskViewModel

    companion object{
        private const val ARG_INDEX = "index"
        fun getInstance(index: Int): QuizAskFragment {
            return QuizAskFragment().apply {
                arguments = Bundle().apply { putInt(ARG_INDEX, index) }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        requireNotNull(inflater).inflate(R.menu.quiz_ask_fragment, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_ask_fragment, container, false)

        initViewModel()

        viewModel.quiz.observe(this, Observer { quiz ->
            quiz?.let { binding.quiz = quiz }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initViewModel() {
        val app = requireNotNull(this.activity).application

        val arg = requireNotNull(arguments).takeIf { it.containsKey(ARG_INDEX) }
        val index = arg?.let { it.getInt(ARG_INDEX) } ?: 0

        val factory = QuizAskViewModelFactory(app, index)
        viewModel = ViewModelProviders.of(this, factory).get(QuizAskViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_item_quiz_ask_edit -> startEditingCurrentQuiz()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startEditingCurrentQuiz(){
        val action =
            QuizAskFragmentDirections.actionQuizAskFragmentToQuizEditFragment(viewModel.currentQuizId)
        this.view!!.findNavController().navigate(action)
    }
}