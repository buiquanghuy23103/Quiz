package com.example.quiz.categorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.CategoryListItemBinding

class CategoryListItem private constructor(private val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CategoryListItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CategoryListItemBinding.inflate(inflater)
            return CategoryListItem(binding)
        }
    }

    fun bind(category: String) {
        with(binding) {
            this.category = category
            root.setOnClickListener {
                val navDirections =
                    CategoryListFragmentDirections.actionCategoryListFragmentToQuizViewPagerFragment(
                        category
                    )
                it.findNavController().navigate(navDirections)
            }
            executePendingBindings()
        }
    }
}