package com.examples.lee.demo.unsplash.overview

import com.examples.lee.models.Collection
import com.examples.lee.mvp.BasePresenter
import com.examples.lee.networking.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// TODO: move this
data class CollectionCellViewModel(val id: String, val title: String, val imgUrl: String?)

class UserCollectionsPresenter(private val feedView: UserCollectionsContract.UserCollectionsView):
        BasePresenter<UserCollectionsContract.UserCollectionsView>(),
        UserCollectionsContract.UserCollectionsPresenter {

    var sampleData: List<CollectionCellViewModel> = listOf()

    override fun onBind(view: UserCollectionsContract.UserCollectionsView) {
        super.onBind(view)

        view.setAdapterDataSource(this)
        fetchData()
    }

    private fun fetchData() {
        // TODO: repo this
        ApiClient.getUnsplashService()
                .getPublicCollectionsForUser("cml446")
                .map { collections: List<Collection> ->
                    collections.map { CollectionCellViewModel(
                            id = it.id,
                            title = it.title.toLowerCase(),
                            imgUrl = it.coverPhoto?.urls?.regular) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { list: List<CollectionCellViewModel> ->
                    sampleData = list
                    feedView.notifyDataChanged() //TODO: smartly diff and update
                }
                //TODO: handle error
        )
    }

    override fun getItemCount(): Int {
        return sampleData.count()
    }

    override fun onBindFeedCellViewAtPosition(position: Int, view: UserCollectionsCellView) {
        val data = sampleData[position]
        view.setTitle(data.title)
        data.imgUrl?.let {  view.setImageUrl(it) }
        view.setOnClickListener { onCellClickedAtPosition(position) }
    }

    private fun onCellClickedAtPosition(position: Int) {
        feedView.startDetailActivity(sampleData[position].id)
    }
}


