package com.example.flicker_search_photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.flicker_search_photos.R
import com.example.flicker_search_photos.glidemodule.GlideApp
import com.example.flicker_search_photos.model.FlickrPhotoResponseModel

class ImageListAdapter(
    val context: Context,
    val dataSet: MutableList<FlickrPhotoResponseModel>,
    val totalPageFetched: Int,
    val totalPage: Int,
    val perPage: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_ACTIVITY = 1
    }

    override fun getItemCount(): Int {
        if (dataSet.count() > 0) {
            if (totalPageFetched < totalPage) {
                return dataSet.count() + 1
            }
            return dataSet.count() + 1
        } else
            return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_LOADING) return
        if (position >= dataSet.count()) {
            holder.itemView.visibility = View.INVISIBLE
            return
        }
        val imageViewHolder = holder as ImageViewHolder
        GlideApp.with(context).load(getImageUrl(dataSet[position])).into(imageViewHolder.imageHolderView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LOADING) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_endless_scroll_row, parent, false)
            LoadingProgressViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_image_list, parent, false)
            return ImageViewHolder(itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= dataSet.count() && totalPageFetched < totalPage)
            VIEW_TYPE_LOADING
        else
            VIEW_TYPE_ACTIVITY
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageHolderView: ImageView = itemView.findViewById(R.id.image_container)
    }

    inner class LoadingProgressViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    private fun getImageUrl(photoResponseModel: FlickrPhotoResponseModel): String{
        return "https://farm${photoResponseModel.farm}.staticflickr.com/${photoResponseModel.server}/${photoResponseModel.id}_${photoResponseModel.secret}_m.jpg"
    }
}