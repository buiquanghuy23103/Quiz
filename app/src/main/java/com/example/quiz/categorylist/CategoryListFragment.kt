package com.example.quiz.categorylist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.CategoryListFragmentBinding
import com.example.quiz.framework.BaseFragment
import kotlinx.android.synthetic.main.category_list_fragment.*
import timber.log.Timber

class CategoryListFragment : BaseFragment<CategoryListViewModel, CategoryListFragmentBinding>() {

    override fun initViewModel() =
        getViewModel { CategoryListViewModel() }

    override fun getLayoutId() = R.layout.category_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.isCategoryListNull().observe(this, Observer { categoryListNull ->
            if (categoryListNull) {
                Timber.i("download data")
                viewModel.downloadCategoryList()
            } else {
                Timber.i("not download data")
            }
        })

        with(category_list) {
            adapter = CategoryListAdapter().apply {
                viewModel.categoryList.observe(this@CategoryListFragment, Observer {
                    submitList(it)
                })
            }
        }
    }
}