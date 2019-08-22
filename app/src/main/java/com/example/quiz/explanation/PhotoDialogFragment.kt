package com.example.quiz.explanation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.quiz.R
import com.example.quiz.databinding.PhotoDialogBinding

class PhotoDialogFragment private constructor() : DialogFragment() {

    companion object {
        private const val ARG_PHOTO_URL = "photo_url"
        fun getInstance(photoUrl: String) = PhotoDialogFragment().apply {
            arguments = Bundle().apply { putString(ARG_PHOTO_URL, photoUrl) }
        }
    }

    private fun getPhotoUrl() = arguments!!.getString(ARG_PHOTO_URL)
        ?: throw Exception("Photo's URL is null")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle(R.string.photo_dialog_title)
                .setView(getDataBinding().root)
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun getDataBinding(): PhotoDialogBinding {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<PhotoDialogBinding>(
            inflater,
            R.layout.photo_dialog,
            null,
            false
        )
        binding.imageUrl = getPhotoUrl()
        return binding
    }
}