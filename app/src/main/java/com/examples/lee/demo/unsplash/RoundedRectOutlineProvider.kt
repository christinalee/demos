package com.examples.lee.demo.unsplash

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider


class RoundedRectOutlineProvider(private val cornerRadius: Float): ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(0, 0, view.width, view.height, cornerRadius)
    }
}
