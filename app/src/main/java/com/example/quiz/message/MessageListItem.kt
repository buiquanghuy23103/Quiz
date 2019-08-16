package com.example.quiz.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.MessageListItemBinding
import com.example.quiz.model.Message

class MessageListItem private constructor(private val binding: MessageListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): MessageListItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding: MessageListItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.message_list_item, parent, false)
            return MessageListItem(binding)
        }
    }

    fun bind(message: Message) {
        with(binding) {
            this.message = message
            executePendingBindings()
        }
    }
}