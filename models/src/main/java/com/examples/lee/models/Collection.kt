package com.examples.lee.models

import com.squareup.moshi.Json

data class Collection(
        val id: String,
        val title: String,
        val description: String? = null,
        @Json(name = "published_at") val publishedAt: String, //TODO: date format
        @Json(name = "updated_at") val updatedAt: String? = null, //TODO: date format
        val curated: Boolean,
        @Json(name = "total_photos") val totalPhotos: Int? = null,
        val private: Boolean,
        @Json(name = "share_key") val shareKey: String? = null,
        @Json(name = "cover_photo") val coverPhoto: Photo?,
        val user: User,
        val urls: Urls,
        val links: Links
)
