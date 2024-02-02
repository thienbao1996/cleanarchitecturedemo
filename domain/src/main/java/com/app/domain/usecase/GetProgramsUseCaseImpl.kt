package com.app.domain.usecase

import android.util.Log
import com.app.domain.ErrorHandler
import com.app.domain.Repository
import com.app.domain.model.Program
import com.app.domain.model.Result
import com.app.domain.model.toResult
import io.reactivex.rxjava3.core.Single

class GetProgramsUseCaseImpl(
    private val repository: Repository,
    private val errorHandler: ErrorHandler
): GetProgramsUseCase {

    override operator fun invoke(): Single<Result<List<Program>>> {
        Log.e("TAG", "GetProgramsUseCase" )
        return repository.getPrograms(URL).toResult(errorHandler)
    }

    companion object {
        private const val URL = "https://static.canal-plus.net/exoplayer/api/programmes.json"
    }
}