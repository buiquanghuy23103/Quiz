package com.example.quiz.message

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.example.quiz.R
import com.example.quiz.databinding.MessageListBinding
import com.example.quiz.firebase.FirebaseAuthUtil
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.framework.BaseFragment
import com.example.quiz.model.Message
import kotlinx.android.synthetic.main.message_list.*

const val RC_SIGN_IN = 1

class MessageFragment : BaseFragment<MessageViewModel, MessageListBinding>(),
    FirebaseDatabaseUtil.Listener, FirebaseAuthUtil.Listener {

    override fun getLayoutId() = R.layout.message_list

    override fun initViewModel() = getViewModel { MessageViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setFirebaseListeners()
        viewModel.initFirebase()
    }

    private fun setFirebaseListeners() {
        viewModel.firebaseDatabaseUtil.listener = this
        viewModel.firebaseAuthUtil.listener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMessageListAdapter()
        setupMessageEditText()
        onSendButtonClick()
    }

    private fun setupMessageListAdapter() {
        with(MessageListAdapter()) {
            message_list.adapter = this
            viewModel.messageList.observe(this@MessageFragment, Observer { messageList ->
                submitList(messageList)
            })

        }
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

    private fun onSendButtonClick() {
        sendButton.setOnClickListener {
            viewModel.sendMessage(messageEditText.text.toString())
            messageEditText.setText("")
        }
    }

    override fun addMessageToList(newMessage: Message) {
        viewModel.sendMessageToList(newMessage)
    }

    override fun startAuthUI(authIntent: Intent) {
        startActivityForResult(authIntent, RC_SIGN_IN)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out_menu_item -> onSignOutOptionSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onSignOutOptionSelected(): Boolean {
        return true
    }
}
