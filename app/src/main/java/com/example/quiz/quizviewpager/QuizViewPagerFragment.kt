package com.example.quiz.quizviewpager

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewPagerFragmentBinding
import com.example.quiz.framework.BaseFragment

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel, QuizViewPagerFragmentBinding>() {
    private val args: QuizViewPagerFragmentArgs by navArgs()

    override fun initViewModel() =
        getViewModel { QuizViewPagerViewModel(args.category) }

    override fun getLayoutId() = R.layout.quiz_view_pager_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.viewPager) {
            viewModel.quizIdList.observe(this@QuizViewPagerFragment, Observer {
                adapter = null
                adapter = QuizViewPagerAdapter(this@QuizViewPagerFragment, it)
            })

            setPageTransformer(ZoomOutPageTransformer())
        }
    }
}
