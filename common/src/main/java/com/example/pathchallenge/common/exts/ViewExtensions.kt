package com.example.pathchallenge.common.exts

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.pathchallenge.common.R
import com.example.pathchallenge.common.RecyclerViewItemDecoration

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

fun RecyclerView.setItemDecorationSpacing(spacePx:Int) {
    addItemDecoration(
        RecyclerViewItemDecoration(spacePx)
    )
}