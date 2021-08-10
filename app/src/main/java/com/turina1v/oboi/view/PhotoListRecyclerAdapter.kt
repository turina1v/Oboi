package com.turina1v.oboi.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.turina1v.oboi.R
import com.turina1v.oboi.data.network.PhotoItem

class PhotoListRecyclerAdapter(private val photos: List<PhotoItem>) : RecyclerView.Adapter<PhotoListRecyclerAdapter.PhotoItemViewHolder>() {

    class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var photoImageView: ImageView? = null
        init {
            photoImageView = itemView.findViewById(R.id.itemPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_photos, parent, false)
        return PhotoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.photoImageView?.let { v ->
            photos[position].webformatURL?.let { url ->
                ImageLoader.loadImage(v, url)
            }
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}