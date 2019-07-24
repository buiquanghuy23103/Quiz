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

class QuizListFragment : BaseFragment<QuizListViewModel>(), QuizListAdapter.OnItemClickListener {
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

        viewModel.quizList.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
                adapter.itemClickListener = this
            }
        })
    }

    override fun onItemViewClick(position: Int) {
        val navDirections = QuizListFragmentDirections
            .actionQuizListFragmentToQuizAskPagerFragment(position)
        this.view!!.findNavController().navigate(navDirections)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu!!, inflater!!)
        inflater.inflate(R.menu.quiz_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_item_quiz_list_add -> startEditingNewQuiz()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startEditingNewQuiz(){
        viewModel.createAndSaveNewQuiz()
        viewModel.createAndSaveNewAnswerList()
        startQuizEditFragmentById(viewModel.newQuizId)
    }

    private fun startQuizEditFragmentById(quizId: Int) {
        val action =
            QuizListFragmentDirections.actionQuizListFragmentToQuizEditFragment(quizId)
        this.view!!.findNavController().navigate(action)
    }
}