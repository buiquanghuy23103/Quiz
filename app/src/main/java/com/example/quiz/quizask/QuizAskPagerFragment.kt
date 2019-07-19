package com.example.quiz.quizask

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.example.quiz.R
import kotlinx.android.synthetic.main.quiz_ask_pager_fragment.*

class QuizAskPagerFragment : Fragment() {
    private val args: QuizAskPagerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_ask_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = QuizAskPagerAdapter(childFragmentManager)
        view_pager.adapter = adapter

        val viewModel = ViewModelProviders.of(this).get(QuizAskPagerViewModel::class.java)
        viewModel.quizList.observe(this, Observer {
            adapter.listSize = it.size
            view_pager.currentItem = args.index
        })
    }

    companion object{
        fun startEditingQuizWithId(id: Int){
            val action = QuizAskPagerFragmentDirections.actionQuizAskPagerFragmentToQuizEditFragment(id)

        }
    }
}
