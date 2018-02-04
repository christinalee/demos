package com.examples.lee.demo.unsplash.overview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.examples.lee.demo.R
import com.examples.lee.models.Photo
import com.squareup.picasso.Picasso

class UserCollectionViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView), UserCollectionsCellView {

    private val title = itemView.findViewById<TextView>(R.id.collectionTitle)
    private val coverPhoto = itemView.findViewById<ImageView>(R.id.collectionCoverPhoto)
    private val image1 = itemView.findViewById<ImageView>(R.id.image1)
    private val image2 = itemView.findViewById<ImageView>(R.id.image2)
    private val image3 = itemView.findViewById<ImageView>(R.id.image3)
    private val image4 = itemView.findViewById<ImageView>(R.id.image4)

    override fun setTitle(title: String) {
        this.title.text = title
    }

    override fun setPhotos(photos: List<Photo>) {
        val picasso = Picasso.with(itemView.context)

        val urls = photos.map { it.urls.regular }
        val imageViews = arrayOf(coverPhoto, image1, image2, image3, image4)

        imageViews
                .zip(urls)
                .forEach {
                    val (photo, url) = it
                    picasso.load(url).into(photo)
                }
    }

    override fun setOnClickListener(listener: () -> Unit) {
        itemView.setOnClickListener { listener.invoke() }
    }
}
