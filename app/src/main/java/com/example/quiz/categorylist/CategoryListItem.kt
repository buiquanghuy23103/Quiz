package com.example.quiz.categorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.CategoryListItemBinding
import com.example.quiz.model.Category

class CategoryListItem private constructor(private val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CategoryListItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding: CategoryListItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.category_list_item, parent, false)
            return CategoryListItem(binding)
        }
    }

    fun bind(category: Category) {
        with(binding) {
            this.category = category
            root.setOnClickListener {
                val navDirections =
                    CategoryListFragmentDirections.actionCategoryListFragmentToQuizViewPagerFragment(
                        category.id
                    )
                it.findNavController().navigate(navDirections)
            }
            executePendingBindings()
        }
    }
}