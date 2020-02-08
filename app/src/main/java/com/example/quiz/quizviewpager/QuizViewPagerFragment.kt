package com.example.quiz.quizviewpager

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.quiz.R
import com.example.quiz.countDownFinnishSignal
import com.example.quiz.countDownInitial
import com.example.quiz.databinding.QuizViewPagerFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_toolbar.view.*
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel, QuizViewPagerFragmentBinding>() {

    private val args: QuizViewPagerFragmentArgs by navArgs()

    override fun initViewModel() =
        getViewModel { QuizViewPagerViewModel(args.categoryId) }

    override fun getLayoutId() = R.layout.quiz_view_pager_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBinding()
        setupToolbar()
        setupQuizView()
        setupTimer()
        viewModel.resetTimer()
    }

    private fun setupBinding() {
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.category = it
        })
    }

    private fun setupTimer() {
        viewModel.timeLeft.observe(viewLifecycleOwner, Observer { timeLeft ->
            if (timeLeft != countDownFinnishSignal) {
                binding.quizToolbar.timeLeft = timeLeft / 1000
                val progress = 100 - (timeLeft / 10 / countDownInitial).toInt()
                quiz_timer_progress_bar.progress = progress
            } else {
                with(binding.viewPager) {
                    val pageCount = adapter?.itemCount ?: 0
                    val currentPageIndex = currentItem
                    currentItem = (currentPageIndex + 1).rem(pageCount)
                }
            }
        })
    }

    private fun setupToolbar() {
        requireActivity().setActionBar(quiz_toolbar.quiz_toolbar_content)
    }

    private val pageChangeCallback = object: ViewPager2.OnPageChangeCallback(){
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            viewModel.resetTimer()
        }
    }

    private fun setupQuizView() {
        with(binding.viewPager) {

            isUserInputEnabled = false

            viewModel.quizIdList.observe(this@QuizViewPagerFragment, Observer {
                adapter = null
                adapter = QuizViewPagerAdapter(this@QuizViewPagerFragment, it)
            })

            this.registerOnPageChangeCallback(pageChangeCallback)

            setPageTransformer(ZoomOutPageTransformer())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}
