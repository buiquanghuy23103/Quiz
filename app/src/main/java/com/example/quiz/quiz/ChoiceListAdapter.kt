package com.example.quiz.quiz

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Choice

class ChoiceListAdapter : ListAdapter<Choice, ChoiceListItem>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Choice>() {
            override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
                return oldItem == newItem
            }
        }
    }

    lateinit var itemClickListener: ChoiceListItem.Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChoiceListItem.from(parent).apply {
            clickListener = itemClickListener
        }

    override fun onBindViewHolder(holder: ChoiceListItem, position: Int) {
        holder.bind(getItem(position))
    }
}