package com.dpforge.skelly

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px

open class SkeletonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    internal val gradientDrawable: GradientDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
    }

    private val actualRadius: Float
        get() = if (cornerRadius < 0) height / 2f else cornerRadius

    /**
     * Color in ARGB format from which gradient animation start.
     *
     * Could be set by `skeleton_start_color` attribute in XML.
     */
    @ColorInt
    var startColor: Int

    /**
     * Color in ARGB format to which gradient animation come
     *
     * Could be set by `skeleton_end_color` attribute in XML.
     */
    @ColorInt
    var endColor: Int

    /**
     * Corner radius in pixels of this skeleton view. Any negative value means that radius equals
     * half of this view height.
     *
     * Could be set by `skeleton_corner_radius` attribute in XML.
     */
    @Px
    var cornerRadius: Float

    init {
        background = gradientDrawable

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.SkeletonView,
            defStyleAttr,
            0
        )
        startColor = typedArray.getColor(
            R.styleable.SkeletonView_skeleton_start_color,
            DEFAULT_START_COLOR
        )
        endColor = typedArray.getColor(
            R.styleable.SkeletonView_skeleton_end_color,
            DEFAULT_END_COLOR
        )
        cornerRadius = typedArray.getDimension(
            R.styleable.SkeletonView_skeleton_corner_radius,
            CORNER_RADIUS_HALF_HEIGHT
        )
        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // This case is most likely for RecyclerView which re-uses its Views. It might be that
        // this view is already got through the layout stage but then detached, recycled, and
        // attached again.
        if (height > 0 && width > 0) {
            SkeletonAnimationCoordinator.attachToAnimationIfNeeded(this)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        SkeletonAnimationCoordinator.detachFromAnimation(this)
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == VISIBLE) {
            SkeletonAnimationCoordinator.attachToAnimationIfNeeded(this)
        } else {
            SkeletonAnimationCoordinator.detachFromAnimation(this)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            gradientDrawable.cornerRadius = actualRadius
            SkeletonAnimationCoordinator.attachToAnimationIfNeeded(this)
        }
    }

    companion object {
        const val DEFAULT_START_COLOR = Color.LTGRAY
        const val DEFAULT_END_COLOR = Color.DKGRAY
        const val CORNER_RADIUS_HALF_HEIGHT = -1f
    }

}