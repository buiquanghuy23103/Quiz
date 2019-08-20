package com.example.quiz.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import timber.log.Timber

class FirebaseStorageUtil {
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val storageReference = firebaseStorage.reference.child("chat_photos")
    private lateinit var uploadTask: UploadTask

    fun uploadSelectedPhoto(selectedPhotoUri: Uri, uploadMessageWithPhotoUrl: (String) -> Unit) {
        val selectedPhotoFilename = selectedPhotoUri.lastPathSegment
            ?: throw Exception("Error retrieving photo filename from URI")
        val photoRefFirebase = storageReference.child(selectedPhotoFilename)
        photoRefFirebase.putFile(selectedPhotoUri)
            .addOnSuccessListener {
                Timber.i("$selectedPhotoFilename is uploaded")
            }
            .addOnFailureListener {
                Timber.e("Error uploading $selectedPhotoFilename: ${it.localizedMessage}")
            }
            .continueWith { task ->
                if (task.isSuccessful) {
                    return@continueWith photoRefFirebase.downloadUrl.result
                } else throw Exception("Error retrieving download URL: ${task.exception}")
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uri = task.result ?: throw Exception("Error getting photo's download URL")
                    val photoUrl = uri.toString()
                    uploadMessageWithPhotoUrl(photoUrl)
                }
            }
    }
}