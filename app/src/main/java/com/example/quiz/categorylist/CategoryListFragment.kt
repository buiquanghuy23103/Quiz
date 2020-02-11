package com.example.quiz.categorylist

import android.content.Intent
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
import com.example.quiz.model.Category
import com.example.quiz.quizList.QuizListActivity
import kotlinx.android.synthetic.main.category_list_fragment.*
import javax.inject.Inject

class CategoryListFragment
    : BaseFragment<CategoryListViewModel, CategoryListFragmentBinding>(),
        CategoryListItem.OnClickListener
{

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
        category_list.adapter = CategoryListAdapter(this).apply {
            viewModel.categoryList.observe(this@CategoryListFragment, Observer {
                submitList(it)
            })
        }
    }

    override fun onCategoryItemClick(category: Category) {

        val intent = Intent(activity, QuizListActivity::class.java)
            .putExtra(getString(R.string.intent_categoryId), category.id)
            .putExtra(getString(R.string.intent_categoryName), category.text)

        startActivity(intent)
    }
}