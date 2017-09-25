package com.examples.lee.demo.unsplash.detail

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.GridLayout.VERTICAL
import android.widget.TextView
import com.examples.lee.demo.R
import com.examples.lee.demo.unsplash.RoundedRectOutlineProvider
import com.examples.lee.mvp.AdapterDataSource
import com.examples.lee.mvp.MvpActivity
import com.makeramen.roundedimageview.RoundedImageView

class CollectionFeedActivity: MvpActivity<CollectionFeedPresenter, CollectionFeedContract.CollectionFeedView>(), CollectionFeedContract.CollectionFeedView {

    lateinit private var adapter: SimpleGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_feed)

        val recyclerView = findViewById<RecyclerView>(R.id.grid)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
    }

    override fun createPresenter(): CollectionFeedPresenter {
        val id: String = intent.getStringExtra("KEY")
        return CollectionFeedPresenter(this, id)
    }

    override fun setAdapterDataSource(adapterDataSource: AdapterDataSource<CollectionFeedCellView>) {
         val recyclerView = findViewById<RecyclerView>(R.id.grid)

        adapter = SimpleGridAdapter(adapterDataSource)
        recyclerView.adapter = adapter
    }

    // TODO: clean this up for the case that the activity is itself the view
    override fun getViewToBind(): CollectionFeedContract.CollectionFeedView {
        return this
    }

    override fun notifyDataChanged() {
        adapter.notifyDataSetChanged()
    }


    class SimpleGridAdapter(private val adapterDataSource: AdapterDataSource<CollectionFeedCellView>):
            RecyclerView.Adapter<SimpleViewHolder>() {

        override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
            adapterDataSource.onBindFeedCellViewAtPosition(position, holder)
        }

        override fun getItemCount(): Int {
            return adapterDataSource.getItemCount()
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): SimpleViewHolder {
            val view = android.view.LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_grid_photo, parent, false)
            return SimpleViewHolder(view)
        }

    }

}
