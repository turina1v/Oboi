package com.turina1v.oboi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.turina1v.oboi.R
import com.turina1v.oboi.data.network.PhotoItem
import com.turina1v.oboi.domain.SearchProps
import kotlinx.android.synthetic.main.activity_photo_list.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoListActivity : AppCompatActivity(), OnPhotoClickListener {
    private val viewModel: PhotoListViewModel by viewModel()
    private val recyclerAdapter = PhotoListRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        initRecycler()

        viewModel.photoListData.observe(this) {
            loaderLayout.isVisible = false
            swipeRefreshLayout.isRefreshing = false
            recyclerAdapter.setData(it)
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPhotoList(SearchProps())
        }
    }

    private fun initRecycler() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = recyclerAdapter
        recyclerView.addItemDecoration(GridItemDecoration(2, 16))
        recyclerAdapter.onPhotoClickListener = this
    }

    override fun onPhotoClick(url: String) {
        startActivity(PhotoDetailsActivity.getStartIntent(this, url))
    }
}