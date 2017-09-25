package com.examples.lee.demo.unsplash.detail

import com.examples.lee.models.Photo
import com.examples.lee.mvp.BasePresenter
import com.examples.lee.networking.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CollectionFeedPresenter(
        val view: CollectionFeedContract.CollectionFeedView,
        private val collectionId: String
): BasePresenter<CollectionFeedContract.CollectionFeedView>(),
        CollectionFeedContract.CollectionFeedPresenter {

    var data: List<Photo> = emptyList()
        set(data) {
            field = data
            view.notifyDataChanged()
        }

    override fun onBind(view: CollectionFeedContract.CollectionFeedView) {
        super.onBind(view)

        view.setAdapterDataSource(this)
        fetchData()
    }

    private fun fetchData() {
        ApiClient.getUnsplashService().getPhotosInCollection(collectionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { list: List<Photo> ->
                            data = list
                        },
                        { e: Throwable ->
                            println("Error $e")
                        }
                )
    }

    override fun onBindFeedCellViewAtPosition(position: Int, view: CollectionFeedCellView) {
        val photo = data[position]

        view.setImageUrl(photo.urls.regular)
        photo.description?.let {
            view.setTitle(it)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
