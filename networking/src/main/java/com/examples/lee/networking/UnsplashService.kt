package com.examples.lee.networking

import com.examples.lee.models.Collection
import com.examples.lee.models.Photo
import com.examples.lee.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    @GET("/photos/curated")
    fun getCuratedPhotos(@Query("page") pageToFetch: Int = 1,
                         @Query("per_page") photosPerPage: Int = 10,
                         @Query("order_by") orderBy: OrderType = OrderType.Latest): Single<List<Photo>>


    @GET("/users/{username}")
    fun getPublicUserProfile(@Path("username") username: String): Single<User>

    @GET("/users/{username}/collections")
    fun getPublicCollectionsForUser(@Path("username") username: String,
                                    @Query("page") pageToFetch: Int = 1,
                                    @Query("per_page") photosPerPage: Int = 10): Single<List<Collection>>

    @GET("/collections/{id}/photos")
    fun getPhotosInCollection(@Path("id") collectionId: String,
                              @Query("page") pageToFetch: Int = 1,
                              @Query("per_page") photosPerPage: Int = 10): Single<List<Photo>>
}
