package com.example.quiz.message

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.quiz.R
import com.example.quiz.databinding.MessageListBinding
import com.example.quiz.framework.BaseFragment
import com.example.quiz.model.Message
import kotlinx.android.synthetic.main.message_list.*

class MessageFragment : BaseFragment<MessageViewModel, MessageListBinding>() {

    override fun getLayoutId() = R.layout.message_list

    override fun initViewModel() = getViewModel { MessageViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMessageList()
        setupMessageEditText()
        setupSendButton()
    }

    private fun setupMessageList() {
        val messageList = mutableListOf<Message>()
        message_list.adapter = MessageListAdapter().apply { submitList(messageList) }
    }

    private fun setupMessageEditText() {
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s.toString().trim().isNotEmpty()
            }
        })
    }

    private fun setupSendButton() {
        sendButton.setOnClickListener {

            messageEditText.setText("")
        }
    }
}
