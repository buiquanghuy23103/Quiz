package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.quiz.R
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizListViewModel

class QuizListFragment : Fragment() {
    private val TAG = "QuizListFragment"

    companion object {
        fun newInstance() = QuizListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.quiz_list_fragment, container, false)
        val viewModel = ViewModelProviders.of(this).get(QuizListViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        var adapter = QuizListAdapter()
        recyclerView.adapter = adapter

        viewModel.quizBank.observe(this, Observer {
            it?.let {
                adapter.quizBank = it
            }
        })

        setHasOptionsMenu(true)
        return view
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
        val newQuiz = Quiz()
        val action = QuizListFragmentDirections.actionQuizListFragmentToQuizEditFragment(newQuiz.id)
        this.view!!.findNavController().navigate(action)
    }
}