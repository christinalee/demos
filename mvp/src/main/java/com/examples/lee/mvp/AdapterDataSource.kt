package com.examples.lee.mvp

interface AdapterDataSource<in V : View> {
    fun onBindFeedCellViewAtPosition(position: Int, view: V)

    fun getItemCount(): Int
}

interface HasAdapter<in S : AdapterDataSource<*>> {
    fun setAdapterDataSource(adapterDataSource: S)
}