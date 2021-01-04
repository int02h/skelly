package com.dpforge.skelly.factory

import androidx.annotation.experimental.Experimental

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@Experimental(level = Experimental.Level.WARNING)
annotation class ExperimentalFactoryApi