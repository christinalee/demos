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
        //TODO: vet number of photos being passed in and accomodate less than 5
        // TODO: remove copy paste loads and do this functionally
        Picasso.with(itemView.context)
                .load(photos[0].urls.regular)
                .into(coverPhoto)

        Picasso.with(itemView.context)
                .load(photos[1].urls.regular)
                .into(image1)

        Picasso.with(itemView.context)
                .load(photos[2].urls.regular)
                .into(image2)

        Picasso.with(itemView.context)
                .load(photos[3].urls.regular)
                .into(image3)

        Picasso.with(itemView.context)
                .load(photos[4].urls.regular)
                .into(image4)

    }

    override fun setOnClickListener(listener: () -> Unit) {
        itemView.setOnClickListener { listener.invoke() }
    }
}
