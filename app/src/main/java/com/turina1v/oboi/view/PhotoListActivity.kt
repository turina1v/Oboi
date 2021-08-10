package com.turina1v.oboi.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.turina1v.oboi.R
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoListActivity : AppCompatActivity() {
    private val viewModel: PhotoListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        viewModel.photoListData.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        }
    }
}