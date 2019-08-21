package com.example.quiz.message

import android.app.Activity.RESULT_OK
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
import timber.log.Timber

const val RC_SIGN_IN = 1
const val RC_PHOTO_PICKER = 2

class MessageFragment : BaseFragment<MessageViewModel, MessageListBinding>(),
    FirebaseDatabaseUtil.Listener, FirebaseAuthUtil.Listener {

    override fun getLayoutId() = R.layout.message_list

    override fun initViewModel() = getViewModel {
        val app = requireNotNull(this.activity).application
        MessageViewModel(app)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setFirebaseListeners()
    }

    private fun setFirebaseListeners() {
        viewModel.setFirebaseDatabaseUtilListener(this)
        viewModel.setFirebaseAuthUtilListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMessageListAdapter()
        setupMessageEditText()
        onSendButtonClick()
        setupPhotoPicker()
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

    private fun setupPhotoPicker() {
        photoPickerButton.setOnClickListener {
            with(Intent()) {
                action = Intent.ACTION_GET_CONTENT
                type = "image/jpeg"
                putExtra(Intent.EXTRA_LOCAL_ONLY, true)
                val intent = Intent.createChooser(this, "Choose a picture to upload")
                startActivityForResult(intent, RC_PHOTO_PICKER)
            }
        }
    }

    override fun replaceWithNewMessageList(messageList: List<Message>) {
        viewModel.updateMessageList(messageList)
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
        viewModel.signOut()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val isValidData = requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK
                && data != null && data.data != null
        if (isValidData) {
            val selectedPhotoUri = data?.data?.also { Timber.i("photoUri = $it") }
                ?: throw Exception("Photo URI not found")
            viewModel.uploadImage(selectedPhotoUri)
        }
    }
}