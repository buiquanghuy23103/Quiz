package com.example.quiz.quizlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.QuizListFragmentBinding
import com.example.quiz.framework.BaseData
import com.example.quiz.framework.BaseFragment
import com.example.quiz.framework.BaseListItem
import com.example.quiz.model.Quiz

class QuizListFragment : BaseFragment<QuizListViewModel, QuizListFragmentBinding>() {

    override fun initViewModel() =
        getViewModel { QuizListViewModel() }

    override fun getLayoutId() = R.layout.quiz_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = QuizListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.quizList.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        adapter.itemClickListener = object : BaseListItem.ClickListener {
            override fun onViewClick(position: Int) {
                startQuizViewPagerFragment(position)
            }

            override fun onDeleteButtonClick(data: BaseData) {
                viewModel.deleteQuiz(data as Quiz)
            }
        }
    }

    fun startQuizViewPagerFragment(position: Int) {
        val navDirections = QuizListFragmentDirections
            .actionQuizListFragmentToQuizAskPagerFragment(position)
        this.view!!.findNavController().navigate(navDirections)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.quiz_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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