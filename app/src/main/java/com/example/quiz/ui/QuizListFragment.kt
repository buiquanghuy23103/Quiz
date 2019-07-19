package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.quiz.R
import com.example.quiz.databinding.QuizListFragmentBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizListViewModel
import kotlinx.android.synthetic.main.quiz_list_fragment.*

class QuizListFragment : Fragment() {
    private lateinit var viewModel: QuizListViewModel
    private lateinit var binding: QuizListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_list_fragment, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = QuizListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(QuizListViewModel::class.java)
        viewModel.quizBank.observe(this, Observer {
            it?.let {
                adapter.quizList = it
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.quiz_list_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_item_quiz_list_add -> startQuizEditFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startQuizEditFragment(){
        val newQuiz = Quiz("New question")
        viewModel.save(newQuiz)
        val action = QuizListFragmentDirections.actionQuizListFragmentToQuizEditFragment(newQuiz.id)
        this.view!!.findNavController().navigate(action)
    }
}