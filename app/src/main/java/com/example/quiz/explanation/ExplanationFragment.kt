package com.example.quiz.explanation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.ExplanationFragmentBinding
import com.example.quiz.firebase.FirebaseAuthUtil
import com.example.quiz.firebase.FirebaseDatabaseUtil
import com.example.quiz.framework.BaseFragment
import com.example.quiz.model.Message
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.explanation_fragment.*
import timber.log.Timber

const val RC_SIGN_IN = 1
const val RC_PHOTO_PICKER = 2
const val DIALOG_KEY = "dialog_key"

class ExplanationFragment : BaseFragment<ExplanationViewModel, ExplanationFragmentBinding>(),
    FirebaseDatabaseUtil.Listener, FirebaseAuthUtil.Listener {

    private val args by navArgs<ExplanationFragmentArgs>()
    private val chatAdapter = ChatAdapter()
    private val compositeDisposable = CompositeDisposable()

    override fun getLayoutId() = R.layout.explanation_fragment

    override fun initViewModel() = getViewModel {
        val app = requireNotNull(this.activity).application
        ExplanationViewModel(app, this, args.quizId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupExplanationText()
        setupMessageList()
        setupMessageEditText()
        onSendButtonClick()
        setupPhotoPicker()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun setupExplanationText() {
        viewModel.quiz.observe(this, Observer {
            binding.quiz = it
        })
    }

    private fun setupMessageList() {
        message_list.isNestedScrollingEnabled = false
        setupMessageListAdapter()
    }

    private fun setupMessageListAdapter() {
        message_list.adapter = chatAdapter.apply {
            viewModel.getAllUsersObservable().subscribe {
                allUsers = it
            }.addTo(compositeDisposable)
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

    override fun updateMessageList(messageList: List<Message>) {
        viewModel.updateMessageList(messageList)
    }

    override fun startAuthUI(authIntent: Intent) {
        startActivityForResult(authIntent, RC_SIGN_IN)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message, menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    override fun onStart() {
        super.onStart()
        chatAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        chatAdapter.stopListening()
        compositeDisposable.dispose()
    }
}
