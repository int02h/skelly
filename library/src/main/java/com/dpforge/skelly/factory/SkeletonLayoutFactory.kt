package com.dpforge.skelly.factory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes

/**
 * Factory to produce skeleton layouts based on real ones in an automatic way
 */
@ExperimentalFactoryApi
class SkeletonLayoutFactory(private val dataFiller: DataFiller) {

    /**
     * Inflates specified layout replacing all non-ViewGroup views with skeletons
     */
    fun inflateFrom(
        context: Context,
        @LayoutRes layoutId: Int,
        parent: ViewGroup? = null,
        attachToRoot: Boolean = parent != null
    ): View {
        // We attach to the parent manually because we want `inflated` to always contain inflated
        // hierarchy not parent in case when attachToRoot == true
        val inflated = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val replaced = if (inflated is ViewGroup) {
            replaceInViewGroup(inflated)
            inflated
        } else {
            createReplacementFor(inflated)
        }
        if (parent != null && attachToRoot) {
            parent.addView(replaced)
        }
        return replaced
    }

    /**
     * Replace all non-ViewGroup views with skeletons in the specified view.
     * **Be careful**, original views that were replaced cannot be returned back.
     */
    fun replaceInViewGroup(viewGroup: ViewGroup) {
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is ViewGroup) {
                replaceInViewGroup(child)
            } else {
                viewGroup.removeViewAt(i)
                viewGroup.addView(createReplacementFor(child), i)
            }
        }
    }

    private fun createReplacementFor(view: View) = SkeletonWrapView(
        dataFiller.fill(view)
    ).apply {
        layoutParams = view.layoutParams
    }

    private fun DataFiller.fill(view: View): View {
        when (view) {
            is TextView -> fillTextView(view)
        }
        return view
    }

    companion object {
        /**
         * Default implementation of [SkeletonLayoutFactory] for more convenient use.
         */
        @ExperimentalFactoryApi
        val Default = SkeletonLayoutFactory(DefaultDataFiller())
    }

}