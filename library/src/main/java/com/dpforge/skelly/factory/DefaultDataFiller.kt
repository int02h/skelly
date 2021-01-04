package com.dpforge.skelly.factory

import android.annotation.SuppressLint
import android.widget.TextView

/**
 * Default implementation of [DataFiller]. It is designed to be extended.
 */
@ExperimentalFactoryApi
open class DefaultDataFiller : DataFiller {

    @SuppressLint("SetTextI18n")
    override fun fillTextView(view: TextView) {
        view.text = "This is Skeleton!"
    }

}