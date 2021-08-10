package com.turina1v.oboi.view

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.turina1v.oboi.R
import okhttp3.OkHttpClient

class ImageLoader {
    companion object {
        private val client = OkHttpClient.Builder().build()

        fun loadImage(imageView: ImageView, url: String) {
            Picasso.get().load(url).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_placeholder_error).into(imageView)
        }
    }
}