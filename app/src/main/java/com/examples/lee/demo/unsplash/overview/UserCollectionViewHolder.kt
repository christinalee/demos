package com.examples.lee.demo.unsplash.overview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.examples.lee.demo.R
import com.squareup.picasso.Picasso

class UserCollectionViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView), UserCollectionsCellView {

    private val title = itemView.findViewById<TextView>(R.id.collectionTitle)
    private val coverPhoto = itemView.findViewById<ImageView>(R.id.collectionCoverPhoto)

    override fun setTitle(title: String) {
        this.title.text = title
    }

    override fun setImageUrl(url: String) {
        Picasso.with(itemView.context)
                .load(url)
                .into(coverPhoto)
    }

    override fun setOnClickListener(listener: () -> Unit) {
        itemView.setOnClickListener { listener.invoke() }
    }
}
