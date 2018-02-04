package com.examples.lee.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView

class RoundedImageMaskView : ImageView {
    private val roundedCornersPath: Path = Path()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        calculatePath(DEFAULT_RADIUS)
    }

    fun setRadius(radius: Float) {
        calculatePath(radius)
    }


    // TODO: this is not an efficient way of rounding an image
    private fun calculatePath(radius: Float) {
        val padding = radius / 2
        val w = this.width
        val h = this.height

        roundedCornersPath.reset()
        roundedCornersPath.addRoundRect(
                RectF(padding, padding, w - padding, h - padding),
                radius,
                radius,
                Path.Direction.CW)

        invalidate()
    }

    public override fun onDraw(c: Canvas) {
        c.clipPath(roundedCornersPath)
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        super.onDraw(c)
    }

    companion object {
        private val DEFAULT_RADIUS = 32F
    }
}
