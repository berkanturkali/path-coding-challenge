package com.example.pathchallenge.common.exts

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.pathchallenge.common.R
import com.example.pathchallenge.common.RecyclerViewItemDecoration
import com.google.android.material.snackbar.Snackbar

fun ImageView.load(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable
) {
    url?.let {
        Glide.with(this).load(it)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_image).into(this)
    }
}

fun RecyclerView.setItemDecorationSpacing(spacePx: Int) {
    addItemDecoration(
        RecyclerViewItemDecoration(spacePx)
    )
}


fun Fragment.showSnack(message: String, color: Int = R.color.primary) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(ContextCompat.getColor(this.requireContext(), color))
        .show()
}