package com.examples.lee.demo.unsplash.overview

import com.examples.lee.models.Photo
import com.examples.lee.mvp.AdapterDataSource
import com.examples.lee.mvp.HasAdapter
import com.examples.lee.mvp.Presenter
import com.examples.lee.mvp.View

interface UserCollectionsCellView : View {
    fun setTitle(title: String)

    fun setPhotos(photos: List<Photo>)

    fun setOnClickListener(listener: () -> Unit)
}

private typealias DataSource = AdapterDataSource<UserCollectionsCellView>
interface UserCollectionsContract {

    interface UserCollectionsView: View, HasAdapter<DataSource> {
        fun notifyDataChanged()

        fun startDetailActivity(collectionId: String)
    }

    interface UserCollectionsPresenter: Presenter<UserCollectionsView>, DataSource
}

