package com.example.quiz.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import timber.log.Timber

class FirebaseStorageUtil {
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val storageReference = firebaseStorage.reference.child("chat_photos")

    fun uploadSelectedPhoto(selectedPhotoUri: Uri, uploadMessageWithPhotoUrl: (String) -> Unit) {
        val selectedPhotoFilename = selectedPhotoUri.lastPathSegment
            ?: throw Exception("Error retrieving photo filename from URI")
        val photoRefFirebase = storageReference.child(selectedPhotoFilename)

        photoRefFirebase.putFile(selectedPhotoUri)
            .addOnSuccessListener {
                Timber.i("$selectedPhotoFilename is uploaded")
                photoRefFirebase.downloadUrl.addOnSuccessListener { uri ->
                    val photoUrl = uri.toString()
                    uploadMessageWithPhotoUrl(photoUrl)
                }
            }
            .addOnFailureListener {
                Timber.e("Error uploading $selectedPhotoFilename: ${it.localizedMessage}")
            }
    }
}