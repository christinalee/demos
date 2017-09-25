package com.examples.lee.models

import com.squareup.moshi.Json

data class Links(
        val self: String? = null, // TODO: don't stringly type
        val html: String? = null,
        val photos: String? = null,
        val likes: String? = null,
        val portfolio: String? = null,
        val related: String? = null,
        val download: String? = null,
        @Json(name = "download_location") val downloadLocation: String? = null
)
