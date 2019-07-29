package com.example.quiz.framework

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<T: BaseViewModel>: Fragment() {
    abstract fun initViewModel(): T
    protected val viewModel by lazy {
        initViewModel()
    }

    inline fun <reified T : BaseViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
        return if (creator == null)
            ViewModelProviders.of(this).get(T::class.java)
        else
            ViewModelProviders.of(this, BaseViewModelFactory(creator)).get(T::class.java)
    }
}