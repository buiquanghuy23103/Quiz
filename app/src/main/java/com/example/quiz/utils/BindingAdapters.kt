package com.example.quiz.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("imageFromUri")
fun bindImageFromUri(view: ImageView, uri: Uri?) {

    if (uri != null) {
        Glide.with(view.context)
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("backgroundImageUrl")
fun bindBackgroundImageUrl(view: View, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {

        val viewTarget = object: CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.background = resource
            }
        }

        Glide.with(view.context)
            .load(imageUrl)
            .centerInside()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(viewTarget)
    }
}

@BindingAdapter("isVisible")
fun showOrHideView(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}