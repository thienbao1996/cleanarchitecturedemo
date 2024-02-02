package com.app.domain.usecase

import com.app.domain.model.Program
import com.app.domain.model.Result
import io.reactivex.rxjava3.core.Single

interface GetProgramsUseCase {
    operator fun invoke(): Single<Result<List<Program>>>
}