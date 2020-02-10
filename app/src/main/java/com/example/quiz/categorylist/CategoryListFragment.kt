package com.example.quiz.categorylist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.databinding.CategoryListFragmentBinding
import com.example.quiz.framework.BaseFragment
import com.example.quiz.getAppInjector
import kotlinx.android.synthetic.main.category_list_fragment.*
import javax.inject.Inject

class CategoryListFragment : BaseFragment<CategoryListViewModel, CategoryListFragmentBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initViewModel(): CategoryListViewModel {
        getAppInjector().inject(this)
        return ViewModelProviders.of(this, viewModelFactory)[CategoryListViewModel::class.java]
    }

    override fun getLayoutId() = R.layout.category_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupCategoryList()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(category_tool_bar)
    }

    private fun setupCategoryList() {
        with(category_list) {
            adapter = CategoryListAdapter().apply {
                viewModel.categoryList.observe(this@CategoryListFragment, Observer {
                    submitList(it)
                })
            }
        }
    }
}