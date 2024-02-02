package com.app.domain.usecase

import com.app.domain.model.Result
import com.app.domain.model.detail.Detail
import io.reactivex.rxjava3.core.Single

interface GetDetailUseCase {
    operator fun invoke(url: String): Single<Result<Detail>>
}