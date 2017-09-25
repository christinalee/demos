package com.examples.lee.demo.unsplash.detail

import com.examples.lee.mvp.AdapterDataSource
import com.examples.lee.mvp.HasAdapter
import com.examples.lee.mvp.Presenter
import com.examples.lee.mvp.View

interface CollectionFeedCellView : View {
    fun setTitle(title: String)

    fun setImageUrl(url: String)
}


private typealias DataSource = AdapterDataSource<CollectionFeedCellView>
interface CollectionFeedContract {
    interface CollectionFeedView: View, HasAdapter<DataSource> {
        fun notifyDataChanged()
    }

    interface CollectionFeedPresenter: Presenter<CollectionFeedView>, DataSource
}

