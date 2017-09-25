package com.examples.lee.models

data class Badge(
        val title: String,
        val primary: Boolean,
        val slug: String,
        val link: String // TODO: don't stringly type
)
