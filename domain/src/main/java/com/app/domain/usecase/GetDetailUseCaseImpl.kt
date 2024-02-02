package com.app.domain.usecase

import com.app.domain.ErrorHandler
import com.app.domain.Repository
import com.app.domain.model.Result
import com.app.domain.model.detail.Detail
import com.app.domain.model.toResult
import io.reactivex.rxjava3.core.Single

class GetDetailUseCaseImpl(
    private val repository: Repository,
    private val errorHandler: ErrorHandler
): GetDetailUseCase {

    override operator fun invoke(url: String): Single<Result<Detail>> {
        return repository.getDetail(url).toResult(errorHandler)
    }
}