package com.examples.lee.networking

enum class OrderType(private val type: String) {
    Latest("latest"),
    Oldest("oldest"),
    Popular("popular");

    override fun toString(): String {
        return this.type
    }
}

