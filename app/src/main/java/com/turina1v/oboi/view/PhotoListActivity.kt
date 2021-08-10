package com.turina1v.oboi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.turina1v.oboi.R
import com.turina1v.oboi.data.network.PhotoItem
import kotlinx.android.synthetic.main.activity_photo_list.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoListActivity : AppCompatActivity() {
    private val viewModel: PhotoListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        viewModel.photoListData.observe(this) {
            loaderLayout.isVisible = false
            initRecycler(it)
        }
    }

    private fun initRecycler(photos: List<PhotoItem>) {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PhotoListRecyclerAdapter(photos)
        recyclerView.addItemDecoration(GridItemDecoration(2, 16))
    }
}