package com.app.domain.model

import com.app.domain.ErrorHandler
import io.reactivex.rxjava3.core.Single

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorEntity) : Result<T>()
}

/**
 * Extension function to transform Single<T> to Single of the Wrapper class Single<Result<T>>
 *     and also log error
 */
fun <T : Any> Single<T>.toResult(errorHandler: ErrorHandler): Single<Result<T>> = this
    .map<Result<T>> {
        Result.Success(it)
    }
    .onErrorReturn {
        val error = errorHandler.getError(it)
        errorHandler.logError(error.toString(), it.localizedMessage?: "An unexpected error occurred", it)
        Result.Error(error)
    }
