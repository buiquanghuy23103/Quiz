package com.example.quiz.message

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quiz.FirebaseUtil
import com.example.quiz.R
import com.example.quiz.model.Message
import kotlinx.android.synthetic.main.message_list.*

class MessageFragment : Fragment() {

    lateinit var firebaseUtil: FirebaseUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseUtil = FirebaseUtil()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.message_list, container, false)
    }

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
        sendButton.isEnabled = true
        sendButton.setOnClickListener {
            val newMessage = Message(text = messageEditText.text.toString())
            firebaseUtil.sendMessage(newMessage)
            messageEditText.setText("")
        }
    }
}
