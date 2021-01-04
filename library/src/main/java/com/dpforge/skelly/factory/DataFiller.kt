package com.dpforge.skelly.factory

import android.widget.TextView

/**
 * Fills views with fake data to make their sizes more natural. Especially important for views
 * with width or height set to `wrap_content`.
 */
@ExperimentalFactoryApi
interface DataFiller {

    fun fillTextView(view: TextView)

}