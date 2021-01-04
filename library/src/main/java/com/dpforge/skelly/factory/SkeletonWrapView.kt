package com.dpforge.skelly.factory

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.dpforge.skelly.SkeletonView

@ExperimentalFactoryApi
@SuppressLint("ViewConstructor")
internal class SkeletonWrapView(
    private val wrappedView: View
) : FrameLayout(wrappedView.context) {

    private val skeletonView = SkeletonView(wrappedView.context)

    private val tmpRect = Rect()
    private val tmpSize = Size()

    init {
        addView(skeletonView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        wrappedView.measure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(wrappedView.measuredWidth, wrappedView.measuredHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            tmpSize.set(width, height)

            wrappedView.layout(0, 0, tmpSize.width, tmpSize.height)

            adjustSize(tmpSize)
            val hOffset = (width - tmpSize.width) / 2
            val vOffset = (height - tmpSize.height) / 2
            skeletonView.layout(hOffset, vOffset, hOffset + tmpSize.width, vOffset + tmpSize.height)
        }
    }

    private fun adjustSize(size: Size) {
        when (wrappedView) {
            is TextView -> {
                val text = wrappedView.text.toString()
                wrappedView.paint.getTextBounds(text, 0, text.length, tmpRect)
                size.setFrom(tmpRect)
            }
            else -> height
        }
    }

    private data class Size(var width: Int = 0, var height: Int = 0) {
        fun setFrom(rect: Rect) {
            width = rect.width()
            height = rect.height()
        }

        fun set(width: Int, height: Int) {
            this.width = width
            this.height = height
        }
    }
}