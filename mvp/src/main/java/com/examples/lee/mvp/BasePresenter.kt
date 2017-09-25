package com.examples.lee.mvp

import android.support.annotation.CallSuper

open class BasePresenter<V : View>: Presenter<V> {

    // TODO: is lateinit a better approach here?
    private var view: V? = null
    protected val isBound
            get() = view != null

    protected fun getView(): V {
        return view ?: throw IllegalStateException("getView() should only be called between bind() and unbind()")
    }

    // -------------------------------------------------------------
    // region       Framework (makes the brain work!) Calls
    // -------------------------------------------------------------
    final override fun create() {
        onCreate()
    }

    final override fun activate() {
        onActivate()
    }

    override fun bind(view: V) {
        onBind(view)
    }

    final override fun unbind() {
        onUnbind()
    }

    final override fun deactivate() {
        onDeactivate()
    }

    final override fun destroy() {
        onDestroy()
    }
    // endregion Framework Calls


    // -------------------------------------------------------------
    // region       Lifecycle calls
    // -------------------------------------------------------------
    @CallSuper
    protected open fun onCreate() {
        // Empty
    }

    @CallSuper
    protected open fun onActivate() {
        // Empty
    }

    @CallSuper
    protected open fun onBind(view: V) {
        this.view = view
    }

    @CallSuper
    protected open fun onUnbind() {
        view = null
    }

    @CallSuper
    protected open fun onDeactivate() {
        // Empty
    }

    @CallSuper
    protected open fun onDestroy() {
        // Empty
    }
    // endregion Lifecycle calls

}
