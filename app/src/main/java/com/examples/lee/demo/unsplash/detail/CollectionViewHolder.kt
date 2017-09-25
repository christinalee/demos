package com.examples.lee.demo.unsplash.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.examples.lee.demo.R
import com.examples.lee.demo.unsplash.RoundedRectOutlineProvider
import com.makeramen.roundedimageview.RoundedImageView

class SimpleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), CollectionFeedCellView {

    private val text = itemView.findViewById<TextView>(R.id.grid_cell_title)
    // TODO: MVP this initialization logic
    private val image = itemView.findViewById<RoundedImageView>(R.id.grid_cell_image).apply {
        val cornerRadPx = SimpleViewHolder.Companion.dpToPx(context, 8).toFloat()
        cornerRadius = cornerRadPx
        outlineProvider = RoundedRectOutlineProvider(cornerRadPx)
    }

    override fun setTitle(title: String) {
        text.text = title
    }

    override fun setImageUrl(url: String) {
        com.squareup.picasso.Picasso.with(itemView.context)
                .load(url)
                .into(image)
    }

    companion object {
        // This is shit
        fun dpToPx(context: android.content.Context, dp: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            return (dp * displayMetrics.density + 0.5).toInt()
        }
    }
}
