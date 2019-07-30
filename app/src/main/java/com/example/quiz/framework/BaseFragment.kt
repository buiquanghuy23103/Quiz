package com.example.quiz.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<T : BaseViewModel, B : ViewDataBinding> : Fragment() {
    lateinit var binding: B
    @LayoutRes
    abstract fun getLayoutId(): Int
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
}