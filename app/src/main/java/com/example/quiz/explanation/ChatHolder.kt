package com.example.quiz.explanation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.alert
import com.example.quiz.databinding.ChatHolderBinding
import com.example.quiz.model.Chat
import com.example.quiz.model.UserProfile

class ChatHolder private constructor(private val binding: ChatHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun from(parent: ViewGroup): ChatHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ChatHolderBinding =
                DataBindingUtil.inflate(inflater, R.layout.chat_holder, parent, false)
            return ChatHolder(binding)
        }
    }

    fun bind(chat: Chat, userProfile: UserProfile) {
        with(binding) {
            this.chat = chat
            this.userProfile = userProfile
            alert("current chat = $chat")
            executePendingBindings()
        }
    }
}