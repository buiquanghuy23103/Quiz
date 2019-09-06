package com.example.quiz.categorylist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.CategoryListFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.category_list_fragment.*

class CategoryListFragment : BaseFragment<CategoryListViewModel, CategoryListFragmentBinding>() {

    override fun initViewModel() =
        getViewModel { CategoryListViewModel() }

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
                    if (it.isNullOrEmpty()) {
                        viewModel.downloadAll()
                    }
                    submitList(it)
                })
            }
        }
    }
}