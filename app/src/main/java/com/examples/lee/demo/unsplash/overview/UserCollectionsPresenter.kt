package com.examples.lee.demo.unsplash.overview

import com.examples.lee.models.Collection
import com.examples.lee.models.Photo
import com.examples.lee.mvp.BasePresenter
import com.examples.lee.networking.ApiClient
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

// TODO: move this
data class CollectionCellViewModel(val id: String, val title: String, val photos: List<Photo>)

class UserCollectionsPresenter(private val feedView: UserCollectionsContract.UserCollectionsView):
        BasePresenter<UserCollectionsContract.UserCollectionsView>(),
        UserCollectionsContract.UserCollectionsPresenter {

    val disposeBag: CompositeDisposable = CompositeDisposable()
    var sampleData: List<CollectionCellViewModel> = listOf()

    override fun onBind(view: UserCollectionsContract.UserCollectionsView) {
        super.onBind(view)

        view.setAdapterDataSource(this)
        fetchData()
    }

    override fun onUnbind() {
        disposeBag.clear()

        super.onUnbind()
    }

    // TODO: repo this
    private fun fetchData() {
        val client = ApiClient.getUnsplashService()

        val listOfCollections = client
                .getPublicCollectionsForUser("cml446")
                .flattenAsFlowable { it }

        val photosInCollection = listOfCollections
                .flatMap {  collection: Collection  ->
                    ApiClient.getUnsplashService()
                            .getPhotosInCollection(collectionId = collection.id)
                            .toFlowable()
                }

        val viewModelsSub = Flowable.zip(
                listOfCollections,
                photosInCollection,
                BiFunction { collection: Collection, photos: List<Photo> ->
                    collection to photos
                })
                .map {
                    val (collection, photos) = it
                    CollectionCellViewModel(collection.id, collection.title, photos)
                }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { viewModels: List<CollectionCellViewModel> ->
                            sampleData = viewModels
                            feedView.notifyDataChanged() //TODO: smartly diff and update
                        },
                        {
                            //TODO: handle error
                        }
                )

        disposeBag.add(viewModelsSub)
    }

    override fun getItemCount(): Int {
        return sampleData.count()
    }

    override fun onBindFeedCellViewAtPosition(position: Int, view: UserCollectionsCellView) {
        val data = sampleData[position]
        view.setTitle(data.title)
        view.setPhotos(data.photos)
        view.setOnClickListener { onCellClickedAtPosition(position) }
    }

    private fun onCellClickedAtPosition(position: Int) {
        feedView.startDetailActivity(sampleData[position].id)
    }
}


