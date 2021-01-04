package com.dpforge.skelly

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator

internal object SkeletonAnimationCoordinator {

    // TODO make customizable through theme
    private const val DEFAULT_ANIMATION_DURATION = 500L

    private val argbEvaluator = ArgbEvaluator()

    private val animatedViews = ArrayList<SkeletonView>()

    var animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = DEFAULT_ANIMATION_DURATION
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener { updateViews(it.animatedValue as Float) }
    }

    fun attachToAnimationIfNeeded(skeletonView: SkeletonView) {
        if (!animator.isStarted) {
            animator.start()
        }
        if (!animatedViews.contains(skeletonView)) {
            animatedViews.add(skeletonView)
        }
    }

    fun detachFromAnimation(skeletonView: SkeletonView) {
        if (animatedViews.remove(skeletonView)) {
            if (animatedViews.isEmpty()) {
                animator.cancel()
            }
        }
    }

    // The passed argument is actually animatedValue but treated like animatedFraction because we
    // need a fraction to calculate intermediate color between start color and end color. With
    // linear interpolator animatedValue and animatedFraction are supposed to be the same but
    // in the future we might want to use non-linear interpolator thus it is important to use
    // exactly animatedValue while animatedFraction is always linear.
    private fun updateViews(fraction: Float) {
        for (i in 0 until animatedViews.size) {
            val view = animatedViews[i]
            val color = argbEvaluator.evaluate(fraction, view.startColor, view.endColor) as Int
            animatedViews[i].gradientDrawable.setColor(color)
        }
    }

}