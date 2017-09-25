package com.examples.lee.mvp

interface Presenter<V : View> {
    fun create()

    fun destroy()

    fun bind(view: V)

    fun unbind()

    fun activate()

    fun deactivate()
}
