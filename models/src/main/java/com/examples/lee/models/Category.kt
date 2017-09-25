package com.examples.lee.models

import com.squareup.moshi.Json

data class Category(
        val id: Int,
        val title: String,
        @Json(name = "photo_count") val photoCount: Int,
        val links: Links
)
