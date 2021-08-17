package com.turina1v.oboi.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.turina1v.oboi.R
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
        initToolbar()
        initRecycler()

        viewModel.photoListData.observe(this) {
            loaderLayout.isVisible = false
            swipeRefreshLayout.isRefreshing = false
            recyclerView.isVisible = true
            recyclerAdapter.setData(it)
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPhotoList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        val filterViewItem = menu?.findItem(R.id.menu_filter)
        filterViewItem?.setOnMenuItemClickListener {
            openSearchSettings(viewModel.searchProps)
            true
        }
        return true
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
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

    private fun openSearchSettings(searchProps: SearchProps) {
        startActivityForResult(SearchSettingsActivity.getStartIntent(this, searchProps), REQUEST_CODE_SETTINGS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SETTINGS && resultCode == RESULT_OK) {
            val searchProps = data?.getParcelableExtra<SearchProps>(SearchSettingsActivity.EXTRA_SEARCH_PROPS)
            searchProps?.let {
                loaderLayout.isVisible = true
                recyclerView.isVisible = false
                viewModel.searchProps = it
                viewModel.getPhotoList()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_SETTINGS = 111
    }
}