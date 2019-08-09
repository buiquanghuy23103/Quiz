package com.example.quiz.quizviewpager

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewPagerFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel, QuizViewPagerFragmentBinding>() {
    private val args: QuizViewPagerFragmentArgs by navArgs()

    override fun initViewModel() =
        getViewModel { QuizViewPagerViewModel() }

    override fun getLayoutId() = R.layout.quiz_view_pager_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.quizIdList.observe(this, Observer { quizIdList ->
            view_pager.adapter = QuizViewPagerAdapter(this, quizIdList)
            view_pager.setPageTransformer(ZoomOutPageTransformer())
            view_pager.currentItem = quizIdList.indexOf(args.quizId)
        })
    }
}
