package com.examples.lee.mvp

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity

abstract class MvpActivity<out P, V> : AppCompatActivity(), View where V : View, P : Presenter<V> {
    private lateinit var presenter: P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = createPresenter()
        presenter.create()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        presenter.bind(getViewToBind())
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        presenter.activate()
    }

    @CallSuper
    override fun onPause() {
        presenter.deactivate()

        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        presenter.unbind()

        super.onStop()
    }


    @CallSuper
    override fun onDestroy() {
        presenter.destroy()

        super.onDestroy()
    }

    protected abstract fun createPresenter(): P
    protected abstract fun getViewToBind(): V
}
