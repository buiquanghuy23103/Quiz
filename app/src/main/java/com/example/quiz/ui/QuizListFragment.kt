package com.example.quiz.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.quiz.R
import com.example.quiz.databinding.QuizItemBinding
import com.example.quiz.model.Quiz
import com.example.quiz.viewmodel.QuizListViewModel

class QuizListFragment : Fragment() {

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
        adapter.quizBank = viewModel.quizBank
        recyclerView.adapter = adapter

        return view
    }
}