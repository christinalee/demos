package com.examples.lee.models

import com.squareup.moshi.Json

data class User(
        val id: String,
        @Json(name = "updated_at") val updatedAt: String? = null, // TODO: Date
        val username: String,
        val name: String,
        @Json(name = "first_name") val firstName: String? = null,
        @Json(name = "last_name") val lastName: String? = null,
        @Json(name = "twitter_username") val twitterUsername: String? = null,
        @Json(name = "portfolio_url") val portfolioUrl: String, // TODO: Don't stringly type
        val bio: String,
        val location: String,
        @Json(name = "total_likes") val totalLikes: Int,
        @Json(name = "total_photos") val totalPhotos: Int,
        @Json(name = "total_collections") val totalCollections: Int,
        @Json(name = "followed_by_user") val followedByUser: Boolean? = null,
        @Json(name = "followers_count") val followersCount: Int? = null,
        @Json(name = "following_count") val followingCount: Int? = null,
        @Json(name = "downloads") val downloads: Int? = null,
        @Json(name = "profile_image") val profileImage: Image,
        @Json(name = "badge") val badge: Badge? = null,
        val links: Links
)
