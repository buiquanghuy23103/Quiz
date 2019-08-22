package com.example.quiz.explanation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.quiz.model.Message

class MessageListAdapter : ListAdapter<Message, MessageListItem>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MessageListItem.from(parent)

    override fun onBindViewHolder(holder: MessageListItem, position: Int) {
        holder.bind(getItem(position))
    }
}
