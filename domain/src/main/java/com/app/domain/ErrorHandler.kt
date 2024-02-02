package com.app.domain

import com.app.domain.model.ErrorEntity

interface ErrorHandler {

    fun logError(
        tag: String,
        message: String,
        throwable: Throwable?
    )

    fun getError(throwable: Throwable): ErrorEntity

}