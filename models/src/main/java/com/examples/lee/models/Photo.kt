package com.examples.lee.models

import com.squareup.moshi.Json


data class Photo(
        val id: String,
        @Json(name = "created_at") val createdAt: String, //TODO: date format
        @Json(name = "updated_at") val updatedAt: String, //TODO: date format
        val width: Int,
        val height: Int,
        val color: String, // TODO: hex
        val likes: Int,
        @Json(name = "liked_by_user") val likedByUser: Boolean,
        val description: String? = null,
        val user: User,
        @Json(name = "current_user_collections") val currentUserCollections: List<Collection>,
        val urls: Urls,
        val links: Links,
        val categories: List<Category>
)

