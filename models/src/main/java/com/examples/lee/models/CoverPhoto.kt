package com.examples.lee.models

import com.squareup.moshi.Json

data class CoverPhoto(
        val id: String,
        val width: Int,
        val height: Int,
        val color: String, //TODO
        val likes: Int,
        @Json(name = "liked_by_user")
        val likedByUser: Boolean,
        val description: String,
        val user: User,
        val urls: Urls,
        val categories: List<Category>? = null,
        val links: Links
)
