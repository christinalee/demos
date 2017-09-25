package com.examples.lee.demo.unsplash.overview

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.examples.lee.demo.R
import com.examples.lee.demo.unsplash.detail.CollectionFeedActivity
import com.examples.lee.demo.unsplash.overview.UserCollectionsContract.UserCollectionsPresenter
import com.examples.lee.demo.unsplash.overview.UserCollectionsContract.UserCollectionsView
import com.examples.lee.mvp.AdapterDataSource
import com.examples.lee.mvp.MvpActivity

class UserCollectionsActivity: MvpActivity<UserCollectionsPresenter, UserCollectionsView>(), UserCollectionsView {

    lateinit private var adapter: SimpleGridAdapter

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_collections)

        val recyclerView = findViewById<RecyclerView>(R.id.grid)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun createPresenter(): UserCollectionsPresenter {
        return UserCollectionsPresenter(this)
    }

    override fun getViewToBind(): UserCollectionsView {
        return this
    }

    override fun setAdapterDataSource(adapterDataSource: AdapterDataSource<UserCollectionsCellView>) {
        val recyclerView = findViewById<RecyclerView>(R.id.grid)
        adapter = SimpleGridAdapter(adapterDataSource)
        recyclerView.adapter = adapter
    }

    override fun notifyDataChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun startDetailActivity(collectionId: String) {
        //TODO: don't use "KEY" as the key
        val intent = Intent(this, CollectionFeedActivity::class.java)
        intent.putExtra("KEY", collectionId)
        startActivity(intent)
    }

    // TODO: improve this
    class SimpleGridAdapter(private val adapterDataSource: AdapterDataSource<UserCollectionsCellView>):
            RecyclerView.Adapter<UserCollectionViewHolder>() {

        override fun onBindViewHolder(holder: UserCollectionViewHolder, position: Int) {
            adapterDataSource.onBindFeedCellViewAtPosition(position, holder)
        }

        override fun getItemCount(): Int {
            return adapterDataSource.getItemCount()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCollectionViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_grid_collection, parent, false)
            return UserCollectionViewHolder(view)
        }

    }

}
