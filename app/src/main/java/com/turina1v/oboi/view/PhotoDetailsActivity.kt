package com.turina1v.oboi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turina1v.oboi.R
import kotlinx.android.synthetic.main.activity_photo_details.*

class PhotoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        val url = intent.getStringExtra(EXTRA_PHOTO_URL)
        url?.let {
            ImageLoader.loadImage(photoView, url)
        }
    }

    companion object {
        fun getStartIntent(context: Context, photoUrl: String): Intent {
            return Intent(context, PhotoDetailsActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_URL, photoUrl)
            }
        }

        private const val EXTRA_PHOTO_URL = "photo_url"
    }
}