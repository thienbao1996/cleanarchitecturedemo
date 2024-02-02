package com.app.domain.di

import com.app.domain.ErrorHandler
import com.app.domain.Repository
import com.app.domain.model.ErrorEntity
import com.app.domain.model.Program
import com.app.domain.model.detail.Detail
import io.reactivex.rxjava3.core.Single

/**
 * Use for mock Repository
 */
class MockRepository : Repository {
    override fun getPrograms(url: String): Single<List<Program>> {
        return Single.create { }
    }

    override fun getDetail(url: String): Single<Detail> {
        return Single.create { }
    }
}

class MockErrorHandler: ErrorHandler {
    override fun logError(tag: String, message: String, throwable: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun getError(throwable: Throwable): ErrorEntity {
        TODO("Not yet implemented")
    }
}