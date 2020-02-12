package com.example.quiz.quizList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.quiz.R
import com.example.quiz.dagger.ViewModelFactory
import com.example.quiz.databinding.ActivityQuizListBinding
import com.example.quiz.utils.countDownInitial
import com.example.quiz.utils.getAppInjector
import com.example.quiz.utils.questionFinishSignal
import kotlinx.android.synthetic.main.activity_quiz_list.*
import kotlinx.android.synthetic.main.quiz_toolbar.view.*
import javax.inject.Inject

class QuizListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: QuizListViewModel
    private lateinit var binding: ActivityQuizListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
        setupBinding()
        setupToolbar()
        setupQuizView()
        setupTimer()
        viewModel.resetTimer()
    }

    private fun initializeViewModel() {
        getAppInjector().inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)[QuizListViewModel::class.java]

        viewModel.withCategoryId(getCategoryId())
        viewModel.withCategoryName(getCategoryName())
    }

    private fun getCategoryId(): String {
        return intent.getStringExtra(getString(R.string.intent_categoryId))
    }

    private fun getCategoryName(): String {
        return intent.getStringExtra(getString(R.string.intent_categoryName))
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz_list)
        viewModel.category().observe(this, Observer {
            binding.category = it
        })
    }

    private fun setupTimer() {
        viewModel.timeLeft.observe(this, Observer { timeLeft ->
            if (timeLeft != questionFinishSignal) {
                binding.quizToolbar.timeLeft = timeLeft / 1000
                val progress = 100 - (timeLeft * 100 / countDownInitial).toInt()
                quiz_timer_progress_bar.progress = progress
            } else {
                with(binding.viewPager) {
                    beginFakeDrag()
                    fakeDragBy(-1f)
                    endFakeDrag()
                }
            }
        })
    }

    private fun setupToolbar() {
        setActionBar(quiz_toolbar.quiz_toolbar_content)
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

            viewModel.quizIdList().observe(this@QuizListActivity, Observer {
                adapter = null
                adapter = QuizViewPagerAdapter(this@QuizListActivity, it)
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
