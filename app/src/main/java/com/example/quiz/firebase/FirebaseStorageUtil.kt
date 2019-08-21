package com.example.quiz.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import timber.log.Timber

object FirebaseStorageUtil {
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val storageReference = firebaseStorage.reference.child("chat_photos")

    fun uploadSelectedPhoto(selectedPhotoUri: Uri, uploadPhotoUrl: (String) -> Unit) {
        val selectedPhotoFilename = selectedPhotoUri.lastPathSegment
            ?: throw Exception("Error retrieving photo filename from URI")
        val photoRefFirebase = storageReference.child(selectedPhotoFilename)
        val uploadPhotoTask = photoRefFirebase.putFile(selectedPhotoUri)

        uploadPhotoTask
            .addOnSuccessListener {
                photoRefFirebase.downloadUrl.addOnSuccessListener { uri ->
                    val photoUrl = uri.toString()
                    uploadPhotoUrl(photoUrl)
                }
            }
            .addOnFailureListener {
                Timber.e("Error uploading $selectedPhotoFilename: ${it.localizedMessage}")
            }
    }
}