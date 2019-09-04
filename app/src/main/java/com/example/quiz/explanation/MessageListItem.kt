package com.example.quiz.explanation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.MessageListItemBinding
import com.example.quiz.model.Chat
import com.example.quiz.model.Message

class MessageListItem private constructor(private val binding: MessageListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var listener: Listener

    companion object {
        fun fromViewGroupWithListener(parent: ViewGroup, listener: Listener): MessageListItem {
            val inflater = LayoutInflater.from(parent.context)
            val binding: MessageListItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.message_list_item, parent, false)
            return MessageListItem(binding).apply { this.listener = listener }
        }
    }

    fun bind(message: Message) {
        with(binding) {
            this.message = message
            isPhotoAvailable = message.photoUrl != null
            imageView.setOnClickListener { listener.startPhotoDialog(message.photoUrl ?: "") }
            executePendingBindings()
        }
    }

    fun bind(message: Chat) {
        with(binding) {

            executePendingBindings()
        }
    }

    interface Listener {
        fun startPhotoDialog(photoUrl: String)
    }
}