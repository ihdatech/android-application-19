package io.github.ihdatech.myapplication.utils

// fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }
fun defaultErrorHandler(): (Throwable) -> Unit = { e -> println(e) }