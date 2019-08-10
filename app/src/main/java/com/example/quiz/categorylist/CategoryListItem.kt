package com.example.quiz.categorylist

import android.view.LayoutInflater
import android.view.View
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
            executePendingBindings()
        }
    }

    private fun createOnClickListener(): OnClickListener {
        return object : OnClickListener {
            override fun startQuizOfCategory(view: View, category: String) {
                val navDirections =
                    CategoryListFragmentDirections.actionQuizListFragmentToQuizAskPagerFragment(
                        category
                    )
                view.findNavController().navigate(navDirections)
            }
        }
    }

    interface OnClickListener {
        fun startQuizOfCategory(view: View, category: String)
    }
}