package com.example.quiz.quizlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.quiz.BaseFragment

import com.example.quiz.R
import com.example.quiz.databinding.QuizListFragmentBinding
import com.example.quiz.model.Answer
import com.example.quiz.model.Quiz

class QuizListFragment : BaseFragment<QuizListViewModel>() {
    private lateinit var binding: QuizListFragmentBinding

    override fun initViewModel(): QuizListViewModel {
        return ViewModelProviders.of(this).get(QuizListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_list_fragment, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = QuizListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.quizBank.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu!!, inflater!!)
        inflater.inflate(R.menu.quiz_list_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_item_quiz_list_add -> startEditingNewQuiz()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startEditingNewQuiz(){
        val newQuiz = createAndSaveNewQuiz()
        createAndSaveNewAnswerList(newQuiz)
        startQuizEditFragmentById(newQuiz.id)
    }

    private fun createAndSaveNewAnswerList(newQuiz: Quiz) {
        val newAnswerList = listOf(
            Answer(newQuiz.id, "", true),
            Answer(newQuiz.id, "", false)
        )
        viewModel.saveAnswerList(newAnswerList)
    }

    private fun createAndSaveNewQuiz(): Quiz {
        val newQuiz = Quiz("New question")
        viewModel.saveQuiz(newQuiz)
        return newQuiz
    }

    private fun startQuizEditFragmentById(quizId: Int) {
        val action =
            QuizListFragmentDirections.actionQuizListFragmentToQuizEditFragment(quizId)
        this.view!!.findNavController().navigate(action)
    }
}