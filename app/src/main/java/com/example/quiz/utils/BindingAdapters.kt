package com.example.quiz.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import timber.log.Timber

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("imageFromUri")
fun bindImageFromUri(view: ImageView, uri: Uri?) {

    if (uri != null) {
        Picasso.get()
            .load(uri)
            .into(view)
    }
}

@BindingAdapter("backgroundImageUrl")
fun bindBackgroundImageUrl(view: View, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {

        val viewTarget = object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Timber.e("Cannot load background image")
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val bitmap = BitmapDrawable(view.resources, bitmap)
                view.background = bitmap
            }
        }

        Picasso.get()
            .load(imageUrl)
            .into(viewTarget)
    }
}

@BindingAdapter("isVisible")
fun showOrHideView(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}