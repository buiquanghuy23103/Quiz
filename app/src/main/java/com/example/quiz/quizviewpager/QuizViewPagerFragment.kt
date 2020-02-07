package com.example.quiz.quizviewpager

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.QuizViewPagerFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.quiz_toolbar.view.*
import kotlinx.android.synthetic.main.quiz_view_pager_fragment.*

class QuizViewPagerFragment : BaseFragment<QuizViewPagerViewModel, QuizViewPagerFragmentBinding>() {

    private val args: QuizViewPagerFragmentArgs by navArgs()

    private val countDownInitial: Long = 20000
    private val countDownInterval: Long = 1000
    private lateinit var countDownTimer: CountDownTimer

    override fun initViewModel() =
        getViewModel { QuizViewPagerViewModel(args.categoryId) }

    override fun getLayoutId() = R.layout.quiz_view_pager_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBinding()
        startTimer()
        setupToolbar()
        setupQuizView()
    }

    private fun setupBinding() {
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.category = it
        })
    }

    private fun startTimer() {

        countDownTimer = object: CountDownTimer(countDownInitial, countDownInterval) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinnished: Long) {
                binding.quizToolbar.timeLeft = millisUntilFinnished / 1000
                val progress = 100 - (millisUntilFinnished * 100 / countDownInitial).toInt()
                quiz_timer_progress_bar.progress = progress
            }
        }.start()

    }

    private fun setupToolbar() {
        requireActivity().setActionBar(quiz_toolbar.quiz_toolbar_content)
    }

    private fun setupQuizView() {
        with(binding.viewPager) {
            viewModel.quizIdList.observe(this@QuizViewPagerFragment, Observer {
                adapter = null
                adapter = QuizViewPagerAdapter(this@QuizViewPagerFragment, it)
            })

            setPageTransformer(ZoomOutPageTransformer())
        }
    }
}
