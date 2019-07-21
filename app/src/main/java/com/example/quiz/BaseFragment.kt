package com.example.quiz

import androidx.fragment.app.Fragment

abstract class BaseFragment<T: BaseViewModel>: Fragment() {
    abstract fun initViewModel(): T
    protected val viewModel by lazy {
        initViewModel()
    }
}